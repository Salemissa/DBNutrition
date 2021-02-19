package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Commune {

    @DatabaseField(generatedId = true)
    @JsonProperty("id")
    private Long id;
    @DatabaseField
    @JsonProperty("communnename")
    private String communename;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    @JsonProperty("moughata")
    private Moughata moughata;
    @JsonIgnore
    @ForeignCollectionField(eager=false)
    private ForeignCollection<Structure> structures;
    @JsonIgnore
    @ForeignCollectionField(eager=false)
    private ForeignCollection<Localite> localites;



    public Commune() {

    }
    public Commune(String communename,Moughata moughata) {
        this.communename = communename;
        this.moughata=moughata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommunename() {
        return communename;
    }

    public void setCommunename(String communename) {
        this.communename = communename;
    }

    public Moughata getMoughata() {
        return moughata;
    }

    public void setMoughata(Moughata moughata) {
        this.moughata = moughata;
    }
    public ForeignCollection<Structure> getStructures() {
        return structures;
    }

    public void setStructures(ForeignCollection<Structure> structures) {
        this.structures = structures;
    }

    public ForeignCollection<Localite> getLocalites() {
        return localites;
    }

    public void setLocalites(ForeignCollection<Localite> localites) {
        this.localites = localites;
    }
}
