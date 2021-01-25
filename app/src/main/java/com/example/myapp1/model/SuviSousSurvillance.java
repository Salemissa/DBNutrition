package com.example.myapp1.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SuviSousSurvillance {
    @DatabaseField(generatedId = true)
    private Long id;
    private  String mois;
    @DatabaseField
    private String annee;
    @DatabaseField
    private  String  age;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Structure structure;
    @DatabaseField
    private int sssebuit;
    @DatabaseField
    private  int venant;
    @DatabaseField
    private int NCG;
    @DatabaseField
    private int NGF;
    @DatabaseField
    private  int Read;
    @DatabaseField
    private int  Gueris;
    @DatabaseField
    private int Deces;
    @DatabaseField
    private int Abonde;
    @DatabaseField
    private int NonRep;
    @DatabaseField
    private int RefCRENI;
    @DatabaseField
    private int TransCRENAS;
}
