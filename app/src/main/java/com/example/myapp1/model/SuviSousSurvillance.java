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
    private int ncg;
    @DatabaseField
    private int ngf;
    @DatabaseField
    private  int read;
    @DatabaseField
    private int  gueris;
    @DatabaseField
    private int deces;
    @DatabaseField
    private int abonde;
    @DatabaseField
    private int nonRep;
    @DatabaseField
    private int refCRENI;
    @DatabaseField
    private int transCRENAS;
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
        return ncg;
    }

    public void setNCG(int NCG) {
        this.ncg = NCG;
    }

    public int getNGF() {
        return ngf;
    }

    public void setNGF(int NGF) {
        this.ngf = NGF;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        read = read;
    }

    public int getGueris() {
        return gueris;
    }

    public void setGueris(int gueris) {
        gueris = gueris;
    }

    public int getDeces() {
        return deces;
    }

    public void setDeces(int deces) {
        deces = deces;
    }

    public int getAbonde() {
        return abonde;
    }

    public void setAbonde(int abonde) {
        abonde = abonde;
    }

    public int getNonRep() {
        return nonRep;
    }

    public void setNonRep(int nonRep) {
        nonRep = nonRep;
    }

    public int getRefCRENI() {
        return refCRENI;
    }

    public void setRefCRENI(int refCRENI) {
        refCRENI = refCRENI;
    }

    public int getTransCRENAS() {
        return transCRENAS;
    }

    public void setTransCRENAS(int transCRENAS) {
        transCRENAS = transCRENAS;
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
