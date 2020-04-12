package com.web_project.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String content;

    public Messages(String content)
    {
        this.content = content;
    }

    @ManyToOne
    private User receiver;

    @ManyToOne
    private User sender;

}
