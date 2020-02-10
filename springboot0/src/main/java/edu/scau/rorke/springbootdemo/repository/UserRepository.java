package edu.scau.rorke.springbootdemo.repository;

import edu.scau.rorke.springbootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rorke
 * @date 2020/2/7 21:54
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
