package com.self.learnjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.self.learnjava.entity.User;

/*
 * SpringBoot整合JPA
 * 继承JpaRepository来完成对数据库的操作
 * UserRepository本身不用做任何操作
 */
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
