package com.web_project.demo.repository;

import com.web_project.demo.model.Messages;
import com.web_project.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages,Long> {
    List<Messages> getAllBySenderEquals(User user);
    List<Messages> getAllByReceiverEquals(User user);
}
