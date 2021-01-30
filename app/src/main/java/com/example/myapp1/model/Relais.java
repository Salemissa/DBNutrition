package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Relais {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String nom;
    @DatabaseField
    private String code;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Animateur animateur;
    @ForeignCollectionField(eager=false)
    private ForeignCollection<Localite> localites;
    public Relais(){};
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Animateur getAnimateur() {
        return animateur;
    }

    public void setAnimateur(Animateur animateur) {
        this.animateur = animateur;
    }

    public ForeignCollection<Localite> getLocalites() {
        return localites;
    }

    public void setLocalites(ForeignCollection<Localite> localites) {
        this.localites = localites;
    }
}
