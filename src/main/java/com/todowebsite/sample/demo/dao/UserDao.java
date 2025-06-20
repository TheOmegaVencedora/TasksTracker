package com.todowebsite.sample.demo.dao;

import com.todowebsite.sample.demo.Entity.Tasks;
import com.todowebsite.sample.demo.Entity.Users;

import java.util.List;

public interface UserDao {

    Users findByUserName(String username);

    void save(Users users);

    void createTask(Tasks tasks);

    List<Tasks> getAllTasks(int userId);

    void delete(int theId);

    void update(Tasks tasks);

    Tasks getTaskById(int id);

}
