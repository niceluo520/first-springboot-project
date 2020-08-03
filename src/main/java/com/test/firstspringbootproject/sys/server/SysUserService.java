package com.test.firstspringbootproject.sys.server;

import com.test.firstspringbootproject.sys.domain.User;

public interface SysUserService {

    User confirmUser(String username);

    User findById(Integer id);
}
