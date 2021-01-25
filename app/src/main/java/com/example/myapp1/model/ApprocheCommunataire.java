package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable
public class ApprocheCommunataire {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String mois;
    @DatabaseField
    private String annee;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    @JsonIgnore
    private byte[] rapport;

    @DatabaseField
    private  int bprouge;
    @DatabaseField
    private  int bpJaune;
    @DatabaseField
    private  int visite;
    @DatabaseField
    private  int menages;
    @DatabaseField
    private  int fammeEnc;
    @DatabaseField
    private  int fammeEncSuvi;
    @DatabaseField(dataType = DataType.STRING)
    private String date;

    @DatabaseField
    private  int NCG;
    @DatabaseField
    private  int testpalu;

    @DatabaseField
    private int paluconfirme;
    @DatabaseField
    private  int TR;

    @DatabaseField
    private int Diarrhee;
    @DatabaseField
    private  int Vaccin;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Localite localite;


}
