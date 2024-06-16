package com.hypad.buysell.serviceImpl;

import com.hypad.buysell.dao.AuthDAO;
import com.hypad.buysell.model.User;
import com.hypad.buysell.service.AuthService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceImpl implements AuthService {

    private final AuthDAO authDAO;

    public ServiceImpl(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    @Override
    public void saveUser(User user) {
        authDAO.saveUser(user);
    }

    @Override
    public boolean ifUserExists(String name, String password, String gmail) {
        return authDAO.compareData(name,password,gmail);
    }

    @Override
    public void deleteUser(Long id) {
        authDAO.deleteUser(id);
    }

    @Override
    public void updateUser(Long id, String newName, String newPass, String newGmail) {
        authDAO.updateUser(id, newName, newPass, newGmail);
    }

    @Override
    public List<User> findUserById(Long id) {
        return authDAO.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return authDAO.findAllUsers();
    }

    @Override
    public List<User> findUserByName(String name) {
        return authDAO.findUserByName(name);
    }
}
