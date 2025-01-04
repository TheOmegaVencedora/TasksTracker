package com.todowebsite.sample.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "users_id")
    private Users users;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "finish_date")
    private LocalDateTime finish_date;

    @Column(name = "status", columnDefinition = "VARCHAR(10) CHECK(status IN ('active', 'inactive'))")
    private String status;




    public Tasks(){

    }

    public Tasks(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDateTime finish_date) {
        this.finish_date = finish_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
