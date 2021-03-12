package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SuperViseur extends Intervenant {


    @DatabaseField
    private  String username;
    @DatabaseField
    private  String password;
    @DatabaseField(unique = true)
    private  String code;
    @ForeignCollectionField(eager=false)
    private ForeignCollection<Animateur> animateurs;
    public  SuperViseur(){};


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

    public ForeignCollection<Animateur> getAnimateurs() {
        return animateurs;
    }

    public void setAnimateurs(ForeignCollection<Animateur> animateurs) {
        this.animateurs = animateurs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
