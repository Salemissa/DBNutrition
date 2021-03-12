package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Arrays;

@DatabaseTable
public class DepistagePassif  {
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
    private  int rouge;
    /*@DatabaseField
    //private  int rougeG;
    @DatabaseField
    private  int rougeF;*/
    @DatabaseField
    private  int Jaune;
    /* @DatabaseField
    private  int JauneF;
    @DatabaseField
    private  int JauneG;

     */
    @DatabaseField
    private  int Vert;
    /*
    @DatabaseField
    private  int VertF;
    @DatabaseField
    private  int VertG;
   */
    @DatabaseField
    private  int Odeme;
    /*
    @DatabaseField
    private  int OdemeG;
    @DatabaseField
    private  int OdemeF;
   */
    @DatabaseField
    private  int zscore;

    @DatabaseField
    private  int zscore2;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Structure structure;



    public DepistagePassif(){

    }

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

    public byte[] getRapport() {
        return rapport;
    }

    public void setRapport(byte[] rapport) {
        this.rapport = rapport;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getRouge() {
        return rouge;
    }

    public void setRouge(int rouge) {
        this.rouge = rouge;
    }



    public int getJaune() {
        return Jaune;
    }

    public void setJaune(int jaune) {
        Jaune = jaune;
    }



    public int getVert() {
        return Vert;
    }

    public void setVert(int vert) {
        Vert = vert;
    }



    public int getOdeme() {
        return Odeme;
    }

    public void setOdeme(int odeme) {
        Odeme = odeme;
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
    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    @Override
    public String toString() {
        return "DepistagePassif{" +
                "mois='" + mois + '\'' +
                ", annee='" + annee + '\'' +
                ", age='" + age + '\'' +
                ", rouge=" + rouge +
                ", Jaune=" + Jaune +
                ", Vert=" + Vert +
                ", Odeme=" + Odeme +
                ", zscore=" + zscore +
                ", zscore2=" + zscore2 +
                ", structure=" + structure +
                '}';
    }


}
