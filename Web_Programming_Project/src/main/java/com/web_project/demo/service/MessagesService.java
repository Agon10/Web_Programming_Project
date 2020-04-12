package com.web_project.demo.service;

import com.web_project.demo.model.Exception.UserNotFoundException;
import com.web_project.demo.model.Messages;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MessagesService {

    void delete(Messages messages);

    Messages sendMessage(String id1, String id2, String message);

    List<Messages> getAllMessagesSentByUser(String userId);

    List<Messages> getAllMessagesReceivedByUser(String userId);
}
