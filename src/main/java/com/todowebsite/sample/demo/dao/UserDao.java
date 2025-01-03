package com.todowebsite.sample.demo.dao;

import com.todowebsite.sample.demo.entity.Tasks;
import com.todowebsite.sample.demo.entity.Users;

public interface UserDao  {



    void save(Users theUsers);

    void addTasks(Tasks tasks);

    Users findUsers(String username);

    //Users findByUsername(String username);


}
