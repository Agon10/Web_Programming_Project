package com.web_project.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Data
public class Language {

    @Id
    private String name;
    @ManyToMany(mappedBy = "languages")
    @JsonBackReference
    private List<User> user;

    public Language (String name)
    {
        this.name = name;
        this.user = new LinkedList<>();
    }

    public User addUser(User user1)
    {
        user.add(user1);
        return user1;
    }

//    public Language(String name, User user) {
//        this.name = name;
//        this.user = user;
//
//    }

}