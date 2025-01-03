package com.todowebsite.sample.demo.dao;

import com.todowebsite.sample.demo.entity.Tasks;
import com.todowebsite.sample.demo.entity.Users;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;


@Repository
public class  UserDaoImp implements UserDao {



   private EntityManager entityManager;

   @Autowired
   public UserDaoImp (EntityManager entityManager){

       this.entityManager = entityManager ;
   }




    @Override
    @Transactional
    public void save(Users theUsers) {

        theUsers.setRole("ROLE_USERS");
        theUsers.setActive(1);

        entityManager.persist(theUsers);



}

    @Override
    @Transactional
    public void addTasks(Tasks tasks){

       tasks.setStart_date(LocalDateTime.now());

       if (tasks.getFinish_date().isBefore(LocalDateTime.now())){
           tasks.setStatus("inactive");
       }else {
           tasks.setStatus("active");
       }
       entityManager.persist(tasks);


}

    @Override
    public Users findUsers(String username) {
        return entityManager.find(Users.class, username);
    }


}
