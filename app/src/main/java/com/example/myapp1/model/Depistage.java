package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String structurerapport;
    @DatabaseField
    private  String rapportname;
    @DatabaseField
    private  int rougeG;
    @DatabaseField
    private  int rougeF;
    @DatabaseField
    private  int jauneF;
    @DatabaseField
    private  int jauneG;
    @DatabaseField
    private  int vertF;
    @DatabaseField
    private  int vertG;
    @DatabaseField
    private  int odemeG;
    @DatabaseField
    private  int odemeF;
    @DatabaseField
    private  int zscore;
    @DatabaseField
    private  int zscore2;
    @DatabaseField
    private String type;

    @DatabaseField
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private String date;
    @DatabaseField
    private int syn;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Structure structure;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Localite localite;
    public Depistage() {};
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public byte[] getRapport() {
        return rapport;
    }

    public void setRapport(byte[] rapport) {
        this.rapport = rapport;
    }

    public int getRougeG() {
        return rougeG;
    }

    public void setRougeG(int rougeG) {
        this.rougeG = rougeG;
    }

    public int getRougeF() {
        return rougeF;
    }

    public void setRougeF(int rougeF) {
        this.rougeF = rougeF;
    }

    public int getJauneF() {
        return jauneF;
    }

    public void setJauneF(int jauneF) {
        this.jauneF = jauneF;
    }

    public int getJauneG() {
        return jauneG;
    }

    public void setJauneG(int jauneG) {
        this.jauneG = jauneG;
    }

    public int getVertF() {
        return vertF;
    }

    public void setVertF(int vertF) {
        this.vertF = vertF;
    }

    public int getVertG() {
        return vertG;
    }

    public void setVertG(int vertG) {
        this.vertG = vertG;
    }

    public int getOdemeG() {
        return odemeG;
    }

    public void setOdemeG(int odemeG) {
        this.odemeG = odemeG;
    }

    public int getOdemeF() {
        return odemeF;
    }

    public void setOdemeF(int odemeF) {
        this.odemeF = odemeF;
    }

    public int getZscore() {
        return zscore;
    }

    public void setZscore(int zscore) {
        this.zscore = zscore;
    }

    public int getZscore2() {
        return zscore2;
    }

    public void setZscore2(int zscore2) {
        this.zscore2 = zscore2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Localite getLocalite() {
        return localite;
    }

    public String getRapportname() {
        return rapportname;
    }

    public void setRapportname(String rapportname) {
        this.rapportname = rapportname;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public String getStructurerapport() {
        return structurerapport;
    }

    public void setStructurerapport(String structurerapport) {
        this.structurerapport = structurerapport;
    }



    public void setSyn(int syn) {
        this.syn = syn;
    }

    public int getSyn() {
        return syn;
    }
}


