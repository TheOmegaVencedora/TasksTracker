package com.todowebsite.sample.demo.service;

import com.todowebsite.sample.demo.Entity.Role;
import com.todowebsite.sample.demo.Entity.Tasks;
import com.todowebsite.sample.demo.Entity.Users;
import com.todowebsite.sample.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{



    @Autowired
    private UserDao userDao;






    @Override
    @Transactional
    public Users findByUserName(String userName) {
       return userDao.findByUserName(userName);
    }



    @Override
    @Transactional
    public void save(Users users) {
        users.setRole(Role.USER);
        users.setActive(1);

        Tasks tasks = null;





        userDao.save(users);
    }

    @Override
    @Transactional
    public void createTask(Tasks tasks) {

        LocalDate finishDateTime = LocalDate.from(tasks.getFinishDate().atTime(23,59,59));
        LocalDate startDateTime = LocalDate.from(tasks.getStartDate().atStartOfDay());

        if (finishDateTime.isAfter(startDateTime)){
            tasks.setStatus("active");
        }else{
            tasks.setStatus("inactive");
        }

        userDao.createTask(tasks);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userDao.findByUserName(username);
        if(users == null){
            throw new UsernameNotFoundException("Invalid username or Password.");
        }
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(),getAuthorities(users.getRole())) ;
    }


    private List<GrantedAuthority> getAuthorities(Role role){
        List<GrantedAuthority> authorities =  new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    public  List<Tasks> getAllTasks(int userId){

        return userDao.getAllTasks(userId);
    }

    @Override
    @Transactional
    public void delete(int theId) {

        userDao.delete(theId);
    }

    @Override
    @Transactional
    public void update(Tasks tasks) {



        userDao.update(tasks);

    }

    @Override
    public Tasks getTaskById(int id) {

        return userDao.getTaskById(id);
    }
}
