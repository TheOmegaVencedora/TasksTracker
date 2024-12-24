package com.todowebsite.sample.demo.dao;

import com.todowebsite.sample.demo.entity.Users;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


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

        entityManager.persist(theUsers);

}

    @Override
    public Users findUsers(String username) {
        return entityManager.find(Users.class, username);
    }


}
