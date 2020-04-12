package com.web_project.demo.service.Impl;

import com.web_project.demo.model.Exception.UserNotFoundException;
import com.web_project.demo.model.Messages;
import com.web_project.demo.model.User;
import com.web_project.demo.repository.MessagesRepository;
import com.web_project.demo.repository.UserRepository;
import com.web_project.demo.service.MessagesService;
import com.web_project.demo.service.UserService;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MessagesImpl implements MessagesService {

    public final MessagesRepository messagesRepository;
    public final UserRepository userRepository;

    public MessagesImpl(MessagesRepository messagesRepository, UserRepository userRepository)
    {
        this.messagesRepository = messagesRepository;
        this.userRepository = userRepository;
    }



    @Override
    public void delete(Messages messages) {
        messagesRepository.delete(messages);

    }

    @Override
    public Messages sendMessage(String id1, String id2,String message) {
        User user1 = userRepository.findById(id1).orElseThrow(UserNotFoundException::new);
        User user2 = userRepository.findById(id2).orElseThrow(UserNotFoundException::new);
        Messages m = new Messages(message);
        m.setReceiver(user2);
        m.setSender(user1);
        messagesRepository.save(m);
        return m;
    }

    @Override
    public List<Messages> getAllMessagesSentByUser(String userId) {
        return messagesRepository.getAllBySenderEquals(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<Messages> getAllMessagesReceivedByUser(String userId) {
        return  messagesRepository.getAllByReceiverEquals(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
    }
}
