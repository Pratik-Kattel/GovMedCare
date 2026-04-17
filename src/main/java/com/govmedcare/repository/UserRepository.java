package com.govmedcare.repository;

import com.govmedcare.model.User;

public interface UserRepository {
    public boolean saveUser(User user);
    public boolean checkByEmail(String email);
}
