package com.govmedcare.repository;
import com.govmedcare.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
     boolean saveUser(User user);
    boolean checkByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> getAllUsers();
    boolean blockUser(Long id);
    boolean updateProfile
}
