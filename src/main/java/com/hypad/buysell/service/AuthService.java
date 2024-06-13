package com.hypad.buysell.service;

import com.hypad.buysell.dao.AuthDAO;
import com.hypad.buysell.model.User;
import org.springframework.stereotype.Service;

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
}
