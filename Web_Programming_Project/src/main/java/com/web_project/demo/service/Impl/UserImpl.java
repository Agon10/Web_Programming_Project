package com.web_project.demo.service.Impl;

import com.web_project.demo.model.Exception.CultureNotFoundException;
import com.web_project.demo.model.Exception.PasswordsDontMatchException;
import com.web_project.demo.model.Exception.UserNameAlreadyExists;
import com.web_project.demo.model.Exception.UserNotFoundException;
import com.web_project.demo.model.Language;
import com.web_project.demo.model.User;
import com.web_project.demo.repository.UserRepository;
import com.web_project.demo.service.LanguageService;
import com.web_project.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {


    private final UserRepository userRepository;
    private final LanguageService languageService;

    public UserImpl(UserRepository userRepository, LanguageService languageService)
    {
        this.userRepository = userRepository;
        this.languageService = languageService;
    }

    @Override
    public User save(String name, String culture, String description, String languages, String password){

       Optional<User> user1 = userRepository.findById(name);
      if(user1.isPresent()) throw new UserNameAlreadyExists();

        String[] language = languages.split(",");
        User user = new User(name,culture,description,password);
        userRepository.save(user);
        for(String s : language)
        {
            Optional<Language> language1 = languageService.findLanguage(s);
            if(language1.isPresent())
            {
                Language language2 = language1.get();
                language2.addUser(user);
                languageService.save(language2);
                user.addLanguage(language2);
            }
            else {
                Language language2 = new Language(s);
                language2.addUser(user);
                languageService.save(language2);
                user.addLanguage(language2);
            }
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(String Id) {
        userRepository.deleteById(Id);
    }

    @Override
    public User getUser(String Id) {
        return userRepository.findById(Id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User login(String name, String password) {

        User user = userRepository.findById(name).orElseThrow(UserNotFoundException::new);
        if(!user.getPassword().equals(password)) throw new PasswordsDontMatchException();

        return userRepository.save(user);
    }

    @Override
    public boolean  followUser(String id1, String id2) {
        User user1 = userRepository.findById(id1).orElseThrow(UserNotFoundException::new);
        User user2 = userRepository.findById(id2).orElseThrow(UserNotFoundException::new);

        boolean exists =user1.getFollowing().contains(user2);
        if(exists) throw new UserNameAlreadyExists();

        boolean added = user1.addUser(user2);


         userRepository.save(user1);
         return  added;
    }

    @Override
    public List<User> getFollowers(String id) {
        User user1 = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return user1.getFollowing();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByCulture(String culture) {
       List<User> userList =userRepository.findAll()
               .stream()
               .filter(e -> e.getCulture().equalsIgnoreCase(culture))
               .collect(Collectors.toList());
       if(userList.size() == 0) throw new CultureNotFoundException();

       return userList;
    }


}
