package com.example.service;

import com.example.controller.request.UpdateUserRequest;
import com.example.controller.response.UserNotFoundException;
import com.example.model.entity.User;
import com.example.model.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    List<User> userList;

    @Autowired
    private UserRepository repo;

    public List<User> getUserList() {
        return (List<User>) repo.findAll();
    }

    public User getUserByName(int id) {
        return repo.searchUserById(id);
    }
    public User getUser(int id){
        Optional<User> user= repo.findById(id);

        return user.get();
    }

    public String saveUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        repo.save(user);
        return "OK";
    }


    public String updateUser(int id, UpdateUserRequest user) {
        repo.updateUser(user.getName(), user.getAge(),id);

        return "redirect:/users";
    }

    public User get(int id) throws UserNotFoundException{
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }

        throw new UserNotFoundException("Could not find any user with id: "+id);

    }

    public void delete(int id) {

        repo.deleteById(id);
    }


}
