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

    @DatabaseField
    private String date;
    @DatabaseField
    private String dateCreation;

    @DatabaseField
    private  int ncg;
    @DatabaseField
    private  int testpalu;

    @DatabaseField
    private int paluconfirme;
    @DatabaseField
    private  int tr;
    @DatabaseField
    private int diarrhee;
    @DatabaseField
    private  int vaccin;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private USB usb;
    @DatabaseField
    int syn;
    @DatabaseField
    private String rapportusb;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getncg() {
        return ncg;
    }

    public void setNCG(int NCG) {
        this.ncg = NCG;
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

    public int getTr() {
        return tr;
    }

    public void setTr(int TR) {
        this.tr = TR;
    }

    public int getDiarrhee() {
        return diarrhee;
    }

    public void setDiarrhee(int diarrhee) {
        this.diarrhee = diarrhee;
    }

    public int getVaccin() {
        return vaccin;
    }

    public void setVaccin(int vaccin) {
        this.vaccin = vaccin;
    }

    public USB getUsb() {
        return usb;
    }

    public void setUsb(USB usb) {
        this.usb = usb;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public int getNcg() {
        return ncg;
    }

    public int getSyn() {
        return syn;
    }

    public void setNcg(int ncg) {
        this.ncg = ncg;
    }

    public void setSyn(int syn) {
        this.syn = syn;
    }

    public void setDateCreation(String dateCreation) {
        dateCreation = dateCreation;
    }

    public String getRapportusb() {
        return rapportusb;
    }

    public void setRapportusb(String rapportusb) {
        this.rapportusb = rapportusb;
    }
}
