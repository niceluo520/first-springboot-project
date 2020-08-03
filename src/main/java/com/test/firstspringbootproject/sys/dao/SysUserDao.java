package com.test.firstspringbootproject.sys.dao;

import com.test.firstspringbootproject.sys.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {

     User confirmUser(String username);

     User findById(Integer id);
}
