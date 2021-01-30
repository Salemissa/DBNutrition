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

    @DatabaseField(dataType = DataType.DATE_STRING,
            format = "yyyy-MM-dd")
    private Date date;

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
        return JauneF;
    }

    public void setJauneF(int jauneF) {
        JauneF = jauneF;
    }

    public int getJauneG() {
        return JauneG;
    }

    public void setJauneG(int jauneG) {
        JauneG = jauneG;
    }

    public int getVertF() {
        return VertF;
    }

    public void setVertF(int vertF) {
        VertF = vertF;
    }

    public int getVertG() {
        return VertG;
    }

    public void setVertG(int vertG) {
        VertG = vertG;
    }

    public int getOdemeG() {
        return OdemeG;
    }

    public void setOdemeG(int odemeG) {
        OdemeG = odemeG;
    }

    public int getOdemeF() {
        return OdemeF;
    }

    public void setOdemeF(int odemeF) {
        OdemeF = odemeF;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }


}
