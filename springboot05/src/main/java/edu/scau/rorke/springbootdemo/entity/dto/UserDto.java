package edu.scau.rorke.springbootdemo.entity.dto;


import java.io.Serializable;

/**
 * @author Rorke
 * @date 2020/2/27 21:23
 */
public class UserDto implements Serializable {
    private int id;
    private String userName;
    private String nickName;
    private String role;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
