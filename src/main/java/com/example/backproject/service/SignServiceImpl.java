package com.example.backproject.service;

import com.example.backproject.dao.SignDao;
import com.example.backproject.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("signService")
public class SignServiceImpl implements SignService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SignDao signDao;

    @Override
    public User getUser(Object param) {
        logger.debug("[SignServiceImpl >> getUser]");
        return signDao.getUser(param);
    }
}
