package edu.scau.rorke.springbootdemo.entity;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Rorke
 * @date 2020/2/12 15:09
 */
@Entity
@Table(name = "tb_user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "salt")
    private String salt;

    public User() {
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt() {
        this.salt =  UUID.randomUUID().toString().replaceAll("-", "");;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", roleId=" + roleId +
                ", salt='" + salt + '\'' +
                '}';
    }
}
