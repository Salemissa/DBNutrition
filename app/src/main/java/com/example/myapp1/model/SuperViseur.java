package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SuperViseur {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String nom;
    @DatabaseField
    private  String usernane;
    @DatabaseField
    private  String password;
    @ForeignCollectionField(eager=false)
    private ForeignCollection<Animateur> animateurs;
    public  SuperViseur(){};
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsernane() {
        return usernane;
    }

    public void setUsernane(String usernane) {
        this.usernane = usernane;
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
}
