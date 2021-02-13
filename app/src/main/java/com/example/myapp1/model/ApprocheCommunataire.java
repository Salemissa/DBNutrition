package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


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

    @DatabaseField(dataType = DataType.DATE_STRING,format = "yyyy-MM-dd")
    private Date date;
    @DatabaseField
    private String DateCreation;

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
    private USB usb;
    public ApprocheCommunataire(){};
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

    public int getBprouge() {
        return bprouge;
    }

    public void setBprouge(int bprouge) {
        this.bprouge = bprouge;
    }

    public int getBpJaune() {
        return bpJaune;
    }

    public void setBpJaune(int bpJaune) {
        this.bpJaune = bpJaune;
    }

    public int getVisite() {
        return visite;
    }

    public void setVisite(int visite) {
        this.visite = visite;
    }

    public int getMenages() {
        return menages;
    }

    public void setMenages(int menages) {
        this.menages = menages;
    }

    public int getFammeEnc() {
        return fammeEnc;
    }

    public void setFammeEnc(int fammeEnc) {
        this.fammeEnc = fammeEnc;
    }

    public int getFammeEncSuvi() {
        return fammeEncSuvi;
    }

    public void setFammeEncSuvi(int fammeEncSuvi) {
        this.fammeEncSuvi = fammeEncSuvi;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNCG() {
        return NCG;
    }

    public void setNCG(int NCG) {
        this.NCG = NCG;
    }

    public int getTestpalu() {
        return testpalu;
    }

    public void setTestpalu(int testpalu) {
        this.testpalu = testpalu;
    }

    public int getPaluconfirme() {
        return paluconfirme;
    }

    public void setPaluconfirme(int paluconfirme) {
        this.paluconfirme = paluconfirme;
    }

    public int getTR() {
        return TR;
    }

    public void setTR(int TR) {
        this.TR = TR;
    }

    public int getDiarrhee() {
        return Diarrhee;
    }

    public void setDiarrhee(int diarrhee) {
        Diarrhee = diarrhee;
    }

    public int getVaccin() {
        return Vaccin;
    }

    public void setVaccin(int vaccin) {
        Vaccin = vaccin;
    }

    public USB getUsb() {
        return usb;
    }

    public void setUsb(USB usb) {
        this.usb = usb;
    }

    public String getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(String dateCreation) {
        DateCreation = dateCreation;
    }
}
