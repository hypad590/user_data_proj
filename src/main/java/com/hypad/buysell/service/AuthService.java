package com.hypad.buysell.service;

import com.hypad.buysell.dao.AuthDAO;
import com.hypad.buysell.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final AuthDAO authDAO;

    public AuthService(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    public void saveUser(User user) {
        authDAO.saveUser(user);
    }
    public boolean ifUserExists(String name, String password) {
        return authDAO.compareData(name, password);
    }
    public void deleteUser(Long id){
        authDAO.deleteUser(id);
    }
    public void updateUser(Long id, String newName, String newPass){authDAO.updateUser(
            id,newName,newPass
    );}
    public List<User> findUserById(Long id){
        return authDAO.findUserById(id);
    }
    public List<User> findAllUsers() {
        return authDAO.findAllUsers();
    }
    public List<User> findUserByName(String name) {
        return authDAO.findUserByName(name);
    }
}
