package com.web_project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.jms.Message;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    private String userName;

    private String culture;
    private String description;
    private String password;

    @ManyToMany
    @JsonIgnore
    private List<User> following;


    @ManyToMany
    @JsonManagedReference
    private List<Language> languages;

    public User(String userName,String culture,String description, String password)
    {
        this.userName = userName;
        this.culture = culture;
        this.description = description;
        this.password=password;
        this.languages = new ArrayList<>();
        this.following =  new ArrayList<>();

    }

    public Language addLanguage(Language language) {
        this.languages.add(language);
        return language;
    }

    public boolean addUser(User user)
    {
        return this.getFollowing().add(user);

    }


}

