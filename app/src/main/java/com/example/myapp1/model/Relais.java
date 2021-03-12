package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Relais extends  Intervenant {

    @DatabaseField
    @JsonProperty("code")
    private String code;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    @JsonIgnore
    private Animateur animateur;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    @JsonProperty("relais")
    private Localite localite;
    public Relais(){};

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

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }
}
