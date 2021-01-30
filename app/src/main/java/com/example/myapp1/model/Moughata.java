package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

@DatabaseTable
public class Moughata {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String moughataname;
    @ForeignCollectionField(eager=false)
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
        return "Moughata{" +
                "moughataname='" + moughataname + '\'' +
                '}';
    }

}
