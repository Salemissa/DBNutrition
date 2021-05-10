package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

@DatabaseTable
public class Moughata {
    @DatabaseField(generatedId = true)
    @JsonProperty("id")
    private Long id;
    @DatabaseField
    @JsonProperty("moughataname")
    private String moughataname;
    @ForeignCollectionField(eager=false)
    @JsonBackReference
    private ForeignCollection<Commune> communes;
    public Moughata() {

    }

    public Moughata(String moughataname) {
        this.moughataname = moughataname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoughataname() {
        return moughataname;
    }

    public void setMoughataname(String moughataname) {
        this.moughataname = moughataname;

    }


    public ForeignCollection<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(ForeignCollection<Commune> communes) {
        this.communes = communes;
    }



    @Override
    public String toString() {
        return this.moughataname;
    }

}
