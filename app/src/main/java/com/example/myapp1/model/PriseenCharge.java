package com.example.myapp1.model;

import com.j256.ormlite.field.DatabaseField;

public class PriseenCharge {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String  age;
    @DatabaseField
    private  String  sexe;
    @DatabaseField
    private  String  statut;
    @DatabaseField
    private  String  contact;
    @DatabaseField
    private  String refere;
    @DatabaseField
    private  int odeme;
    @DatabaseField
    private  String pec;
    @DatabaseField
    private  String nomaccompagnant;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Localite localite;
}
