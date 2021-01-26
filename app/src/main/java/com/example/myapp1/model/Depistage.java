package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Depistage {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String mois;
    @DatabaseField
    private String annee;
    @DatabaseField
    private  String  age;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    @JsonIgnore
    private byte[] rapport;
    @DatabaseField
    private  int rougeG;
    @DatabaseField
    private  int rougeF;
    @DatabaseField
    private  int JauneF;
    @DatabaseField
    private  int JauneG;
    @DatabaseField
    private  int VertF;
    @DatabaseField
    private  int VertG;
    @DatabaseField
    private  int OdemeG;
    @DatabaseField
    private  int OdemeF;

    @DatabaseField
    private  int zscore;

    @DatabaseField
    private  int zscore2;
    @DatabaseField
    private String type;
    @DatabaseField
    private Date date;

    //@DatabaseField(foreign = true, foreignAutoRefresh = true,canBeNull = true)
    private Structure structure;
    //@DatabaseField(foreign = true, foreignAutoRefresh = true)
    //private Localite localite;

}
