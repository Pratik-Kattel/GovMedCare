package com.govmedcare.service;

import com.govmedcare.dao.UserDao;
import com.govmedcare.exception.InvalidCredentialsException;
import com.govmedcare.exception.UserAlreadyExistsException;
import com.govmedcare.exception.UserBlockedException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.User;
import com.govmedcare.types.UserRole;
import com.govmedcare.types.UserStatus;
import com.govmedcare.utils.HashPasswordUtility;
import com.govmedcare.validator.UserValidator;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    public boolean blockUserService(Long id) {
        if (id <= 0) {
            throw new UserDoesNotExistsException("Invalid user, please try again");
        }
        return userDao.blockUser(id);
    }

    public boolean updateUserInfoService(User user) {
        if (user == null) {
            throw new UserDoesNotExistsException("Invalid user, please try again !");
        }
        return userDao.updateProfile(user);
    }

    public int getActiveUsers() {
        return userDao.getActiveUsers();
    }

    public int getBlockedUsers() {
        return userDao.getBlockedUsers();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

}

