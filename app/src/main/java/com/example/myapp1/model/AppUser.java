package com.example.myapp1.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
@DatabaseTable
public class AppUser {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String username;
    @DatabaseField
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @DatabaseField
    private boolean actived;




    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public boolean isActived() {
        return actived;
    }
    public void setActived(boolean actived) {
        this.actived = actived;
    }



    public AppUser() {

    }

    public AppUser(String username, String password, boolean actived) {
        this.username = username;
        this.password = password;
        this.actived = actived;


    }
}
