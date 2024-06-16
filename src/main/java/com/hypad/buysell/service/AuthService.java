package com.hypad.buysell.service;

import com.hypad.buysell.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {
    void saveUser(User user);
    boolean ifUserExists(String name, String password);
    void deleteUser(Long id);
    void updateUser(Long id, String newName, String newPass);
    List<User> findUserById(Long id);
    List<User> findAllUsers();
    List<User> findUserByName(String name);
}
