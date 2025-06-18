package com.todowebsite.sample.demo.dao;

import com.todowebsite.sample.demo.Entity.Tasks;
import com.todowebsite.sample.demo.Entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{



    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Modifying
    public Users findByUserName(String username) {

        TypedQuery<Users> query = entityManager.createQuery("SELECT i FROM Users i WHERE i.username = :username", Users.class);

        query.setParameter("username", username);

        Users theUser = null;

        try {
            theUser = (Users) query.getSingleResult();

            System.out.println("Users Object: " +theUser);
        } catch (Exception e){
            return null;


        }
        return theUser;
    }

    @Override
    public void save(Users users) {

        entityManager.persist(users);
    }

    @Override
    public void createTask(Tasks tasks) {

         entityManager.persist(tasks);
    }

    @Override
    public List<Tasks> getAllTasks(int userId) {
        TypedQuery<Tasks> query = entityManager.createQuery("SELECT t FROM Tasks t WHERE t.users.id = :userId", Tasks.class);
        query.setParameter("userId", userId);

        return query.getResultList();

    }

    @Override
    public void delete(int id) {
        Tasks tasks = entityManager.find(Tasks.class, id);

        if (tasks != null){
            entityManager.remove(tasks);
        }
    }

    @Override
    public void update(Tasks tasks) {
       // Tasks existed = entityManager.find(Tasks.class, tasks.getId());

//        if (existed != null){
//            existed.setTitle(tasks.getTitle());
////            existed.setStartDate(tasks.getStartDate());
////            existed.setFinishDate(tasks.getFinishDate());
////
////            if (existed.getFinishDate().isAfter(existed.getStartDate())){
////                tasks.setStatus("active");
////            }else {
////                tasks.setStatus("inactive");
////            }
//
//        }


        entityManager.merge(tasks);
    }

    @Override
    public Tasks getTaskById(int id) {
        return entityManager.find(Tasks.class, id);
    }

//    @Override
//    public void delete(int id) {
//       TypedQuery<Tasks> query = entityManager.createQuery("DELETE FROM Tasks t WHERE t.id = :id", Tasks.class);
//
//       query.setParameter("id", id);
//       query.executeUpdate();
//    }


}
