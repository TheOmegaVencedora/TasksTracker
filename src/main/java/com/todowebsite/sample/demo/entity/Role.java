package com.todowebsite.sample.demo.Entity;

public enum Role {
    USER("ROLE_USER");

    private String authority;

    Role(String authority){
        this.authority = authority;
    }

    public String getAuthority(){
        return authority;
    }


}
