package edu.scau.rorke.springbootdemo.dao;

import edu.scau.rorke.springbootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rorke
 * @date 2020/2/12 15:13
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    User findByNickName(String nickName);
}
