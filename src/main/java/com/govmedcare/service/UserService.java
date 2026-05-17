package com.govmedcare.service;
import com.govmedcare.dao.UserDao;
import com.govmedcare.exception.InvalidCredentialsException;
import com.govmedcare.exception.UserAlreadyExistsException;
import com.govmedcare.exception.UserBlockedException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.User;
import com.govmedcare.types.UserRole;
import com.govmedcare.types.UserStatus;
import com.govmedcare.validator.UserValidator;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {
    UserDao userDao=new UserDao();
    public  boolean registerUserService(User user) {
        boolean existByEmail = userDao.checkByEmail(user.getEmail());
        if (existByEmail) {
            throw new UserAlreadyExistsException("User already exists please login to continue");
        }
        final String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        user.setRole(UserRole.PATIENT);
        user.setStatus(UserStatus.active);
        return userDao.saveUser(user);
    }
    public User LoginUserService(String email,String password){
        User existingUser=userDao.findByEmail(email).orElseThrow(()->new UserDoesNotExistsException("User with this email doesn't exists please register to continue"));
        if(existingUser.getStatus()==UserStatus.blocked){
            throw new UserBlockedException("Account blocked, please contact administration for support");
        }
        boolean validPassword=BCrypt.checkpw(password,existingUser.getPassword());
        if(!validPassword){
            throw new InvalidCredentialsException("Invalid Credentials provided");
        }
        existingUser.setPassword(null);
        return existingUser;
    }

    public boolean blockUserService(Long id){
        if(id<=0){
            throw new UserDoesNotExistsException("Invalid user, please try again");
        }
        return  userDao.blockUser(id);
    }

    public boolean updateUserInfoService(User user){
        if(user==null){
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

