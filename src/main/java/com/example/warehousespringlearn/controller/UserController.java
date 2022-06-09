package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Supplier;
import com.example.warehousespringlearn.entity.User;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.payload.UserDto;
import com.example.warehousespringlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<User> getAllUsers(@RequestParam int page){
        return userService.getAllUser(page);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping
    public Result addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.editUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

}
