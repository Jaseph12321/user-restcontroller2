package com.example.controller;


import com.example.controller.request.UpdateUserRequest;
import com.example.controller.response.UserNotFoundException;
import com.example.model.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    //Get all users
    @GetMapping()
    String getUser(Model model) {
        List<User> userList = this.userService.getUserList();
        model.addAttribute("userList", userList);
        return "user";
    }

    //Get one specific user
    @GetMapping("/search")
    String getUser(Model model, @RequestParam int id){
        User userList = this.userService.getUserByName(id);
        model.addAttribute("userList", userList);
        return "user";
    }


    // create new user
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new user");
        return "create_user_form";
    }

    @PostMapping("/save_new")
    public String saveUser(@RequestParam String name, @RequestParam int age) {
        this.userService.saveUser(name, age);
        return "redirect:";
    }

    // update one user
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        try {
            User user = this.userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit user");

            return "edit_user_form";
        } catch (UserNotFoundException e) {
            return "redirect:/users";
        }
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public String updateUser(@PathVariable int id, @RequestBody UpdateUserRequest updateUserRequest) {
        String result = this.userService.updateUser(id,updateUserRequest);
        return result;
    }

    //delete user
    @GetMapping("/delete_form/{id}")    // confirm if or not to delete
    public String showDeleteForm(@PathVariable("id") int id, Model model) {
        try {
            User user = this.userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Delete user");

            return "delete_user_form";
        } catch (UserNotFoundException e) {
            return "redirect:/users";
        }
    }

    @DeleteMapping("/delete/{id}")  // confirm to delete
    public void deleteUser(@PathVariable("id") int id) {
        this.userService.delete(id);
    }

}