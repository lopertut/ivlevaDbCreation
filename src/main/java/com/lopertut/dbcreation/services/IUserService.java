package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
