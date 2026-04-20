package com.govmedcare.service;

import com.govmedcare.dao.UserDao;
import com.govmedcare.exception.UserAlreadyExistsException;
import com.govmedcare.model.User;
import com.govmedcare.types.UserRole;
import com.govmedcare.types.UserStatus;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class AuthService {
    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registerUserService(User user) {
            boolean existByEmail = userDao.checkByEmail(user.getEmail());
            if (existByEmail) {
                throw new UserAlreadyExistsException("User already exists please login to continue");
            }
            final String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            user.setRole(UserRole.PATIENT);
            user.setStatus(UserStatus.ACTIVE);
           return userDao.saveUser(user);
        }
    }

