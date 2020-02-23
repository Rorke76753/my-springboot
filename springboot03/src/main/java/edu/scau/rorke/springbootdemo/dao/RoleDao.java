package edu.scau.rorke.springbootdemo.dao;

import edu.scau.rorke.springbootdemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {
}
