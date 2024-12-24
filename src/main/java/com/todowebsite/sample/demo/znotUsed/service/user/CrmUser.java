package com.todowebsite.sample.demo.znotUsed.service.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CrmUser {

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

   @NotNull(message = "is required")
   @Size(min = 1, message = "is required")
   private String lastName;

   @NotNull(message = "is required")
   @Size(min = 1, message = "is required")
   private String username;

   @NotNull(message = "is required")
   @Size(min = 5, message = "is required")
   private String password;


   public CrmUser(){

   }


    public CrmUser(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



