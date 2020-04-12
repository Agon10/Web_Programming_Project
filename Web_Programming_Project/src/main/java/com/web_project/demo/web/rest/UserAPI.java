package com.web_project.demo.web.rest;

import com.web_project.demo.model.User;
import com.web_project.demo.service.UserService;
import org.springframework.data.web.JsonPath;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path ="/users", produces = MimeTypeUtils.APPLICATION_JSON_VALUE )
public class UserAPI {
    private final UserService userService;


    public UserAPI(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/culture")
    public List<User> getUsersByCulture(@RequestParam(value = "cult") String cult)
    {

        return userService.getUsersByCulture(cult);
    }


    @PostMapping("/register")
    public User createAccount(
                              @RequestParam(value = "name")String name,
                              @RequestParam(value = "culture") String culture,
                              @RequestParam(value = "culture_description") String description,
                              @RequestParam(value = "languages") String languages,
                              @RequestParam(value = "password") String password)
    {

      return userService.save(name,culture,description,languages,password);

    }

    @PostMapping("/login")
    public User loginAccount(
                             @RequestParam(value = "name")String name,
                             @RequestParam(value = "password") String password)
    {
        return userService.login(name,password);
    }

    @PostMapping("follow/{id1}/{id2}")
    public boolean followUser(@PathVariable String id1, @PathVariable String id2){
       return userService.followUser(id1,id2);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id)
    {
        return userService.getUser(id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id)
    {
        userService.delete(id);
    }

    @GetMapping("/followers/{id}")
    public List<User> getFollowers(@PathVariable String id)
    {
         return userService.getFollowers(id);
    }


}
