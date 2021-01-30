package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Animateur {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String nom;
    @DatabaseField
    private String code;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private SuperViseur superViseur;
    @ForeignCollectionField(eager=false)
    private ForeignCollection<Relais> relais;
    public Animateur(){};
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

    public SuperViseur getSuperViseur() {
        return superViseur;
    }

    public void setSuperViseur(SuperViseur superViseur) {
        this.superViseur = superViseur;
    }

    public ForeignCollection<Relais> getRelais() {
        return relais;
    }

    public void setRelais(ForeignCollection<Relais> relais) {
        this.relais = relais;
    }
}
