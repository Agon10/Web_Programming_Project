package com.web_project.demo.service;

import com.web_project.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User save(String name,String culture, String description,String language,String password);
    void delete(String id);

    User getUser(String Id);

    User login(String name,String password);

    boolean followUser(String id1, String id2);

    List<User> getFollowers(String id);

    List<User> getUsers();

    List<User> getUsersByCulture(String culture);
}
