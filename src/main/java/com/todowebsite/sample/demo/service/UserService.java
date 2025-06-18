package com.todowebsite.sample.demo.service;

import com.todowebsite.sample.demo.Entity.Tasks;
import com.todowebsite.sample.demo.Entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users findByUserName(String userName);

    void save(Users users);

    void createTask(Tasks tasks);

    List<Tasks> getAllTasks(int userId);

    void delete(int theId);

    void update(Tasks tasks);

    Tasks getTaskById(int id);
}
