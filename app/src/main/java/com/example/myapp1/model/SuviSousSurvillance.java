package com.example.myapp1.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class SuviSousSurvillance {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String mois;
    @DatabaseField
    private String annee;
    @DatabaseField
    private  String  age;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Structure structure;
    @DatabaseField
    private int ssdebuit;
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
    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd")
    private Date date;

    public SuviSousSurvillance (){};

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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public int getVenant() {
        return venant;
    }

    public void setVenant(int venant) {
        this.venant = venant;
    }

    public int getNCG() {
        return NCG;
    }

    public void setNCG(int NCG) {
        this.NCG = NCG;
    }

    public int getNGF() {
        return NGF;
    }

    public void setNGF(int NGF) {
        this.NGF = NGF;
    }

    public int getRead() {
        return Read;
    }

    public void setRead(int read) {
        Read = read;
    }

    public int getGueris() {
        return Gueris;
    }

    public void setGueris(int gueris) {
        Gueris = gueris;
    }

    public int getDeces() {
        return Deces;
    }

    public void setDeces(int deces) {
        Deces = deces;
    }

    public int getAbonde() {
        return Abonde;
    }

    public void setAbonde(int abonde) {
        Abonde = abonde;
    }

    public int getNonRep() {
        return NonRep;
    }

    public void setNonRep(int nonRep) {
        NonRep = nonRep;
    }

    public int getRefCRENI() {
        return RefCRENI;
    }

    public void setRefCRENI(int refCRENI) {
        RefCRENI = refCRENI;
    }

    public int getTransCRENAS() {
        return TransCRENAS;
    }

    public void setTransCRENAS(int transCRENAS) {
        TransCRENAS = transCRENAS;
    }

    public int getSsdebuit() {
        return ssdebuit;
    }

    public void setSsdebuit(int ssdebuit) {
        this.ssdebuit = ssdebuit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
