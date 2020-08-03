package com.test.firstspringbootproject.sys.server.imp;

import com.test.firstspringbootproject.sys.dao.SysUserDao;
import com.test.firstspringbootproject.sys.domain.User;
import com.test.firstspringbootproject.sys.server.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public User confirmUser(String username) {
        return sysUserDao.confirmUser(username);
    }

    @Override
    public User findById(Integer id) {
        return sysUserDao.findById(id);
    }
}
