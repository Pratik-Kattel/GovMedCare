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

public class AuthService {
    private UserDao userDao = new UserDao();
    public boolean registerUserService(User user) {
        boolean existByEmail = userDao.checkByEmail(user.getEmail());
        if (existByEmail) {
            throw new UserAlreadyExistsException("User already exists please login to continue");
        }
        String hashedPassword = HashPasswordUtility.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole(UserRole.PATIENT);
        user.setStatus(UserStatus.active);
        return userDao.saveUser(user);
    }

    public User LoginUserService(String email, String password) {
        User existingUser = userDao.findByEmail(email).orElseThrow(() -> new UserDoesNotExistsException(
                "User with this email doesn't exists please register to continue"));
        if (existingUser.getStatus() == UserStatus.blocked) {
            throw new UserBlockedException("Account blocked, please contact administration for support");
        }
        // Verify password using utility class
        boolean validPassword = HashPasswordUtility.verifyPassword(password, existingUser.getPassword());
        if (!validPassword) {
            throw new InvalidCredentialsException("Invalid Credentials provided");
        }
        // Remove password before returning user object
        existingUser.setPassword(null);
        return existingUser;
    }

}
