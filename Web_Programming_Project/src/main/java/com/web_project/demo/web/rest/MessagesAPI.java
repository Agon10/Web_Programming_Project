package com.web_project.demo.web.rest;

import com.web_project.demo.model.Messages;
import com.web_project.demo.service.MessagesService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/user/post", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class MessagesAPI {
    private final MessagesService messagesService;

    public MessagesAPI(MessagesService messagesService)
    {
        this.messagesService = messagesService;
    }

    @PostMapping
    public Messages sendMessage(@RequestParam(value = "user_id1") String user_id1,
                                @RequestParam(value = "user_id2") String user_id2,
                                @RequestParam(value = "message") String message) {
       return  messagesService.sendMessage(user_id1,user_id2,message);
    }

    @GetMapping(path = "/sender/{id}")
    public List<Messages> getMessagesSentBy(@PathVariable String id) {
        return messagesService.getAllMessagesSentByUser(id);
    }
    @GetMapping(path = "/receiver/{id}")
    public List<Messages> getMessagesReceivedBy(@PathVariable String id){
        return messagesService.getAllMessagesReceivedByUser(id);
    }
}
