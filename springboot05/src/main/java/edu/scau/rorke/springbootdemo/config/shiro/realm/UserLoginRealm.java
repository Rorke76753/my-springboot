package edu.scau.rorke.springbootdemo.config.shiro.realm;

import edu.scau.rorke.springbootdemo.entity.User;
import edu.scau.rorke.springbootdemo.service.impl.UserLoginServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author Rorke
 * @date 2020/2/27 23:23
 */
public class UserLoginRealm extends AuthorizingRealm {
    private final String HASH_ALGORITHM_NAME = "MD5";
    private final int HASH_ITERATION_TIMES = 6;

    @Autowired
    private UserLoginServiceImpl loginService;

    public UserLoginRealm() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(HASH_ITERATION_TIMES);
        matcher.setHashAlgorithmName(HASH_ALGORITHM_NAME);
        this.setCredentialsMatcher(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User currentUser = (User) principalCollection.getPrimaryPrincipal();
        int roleId = currentUser.getRoleId()-1;
        List<String> roleName = loginService.roleNames;
        info.addStringPermission(roleName.get(roleId));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = loginService.getUserByUsername(token.getUsername());
        if (user==null) {
            throw new UnknownAccountException("用户不存在");
        }else {
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());
            return new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
        }
    }
}
