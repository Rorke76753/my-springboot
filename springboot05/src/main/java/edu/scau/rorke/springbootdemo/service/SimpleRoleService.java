package edu.scau.rorke.springbootdemo.service;

import edu.scau.rorke.springbootdemo.dao.RoleDao;
import edu.scau.rorke.springbootdemo.entity.Role;
import edu.scau.rorke.springbootdemo.entity.dto.UserDto;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rorke
 * @date 2020/2/29 15:00
 */
public abstract class SimpleRoleService {
    private final RoleDao dao;
    public  List<String> roleNames;
    public SimpleRoleService(RoleDao dao) {
        this.dao = dao;
        getRoleNameList();
    }

    private void getRoleNameList(){
        List<Role> roles = dao.findAll();
        roleNames = new ArrayList<>();
        for (Role role: roles) {
            roleNames.add(role.getTypeName());
        }
    }
}
