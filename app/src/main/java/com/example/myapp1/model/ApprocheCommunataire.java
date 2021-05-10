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
    private Long idu;
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
    @DatabaseField
    private  int ncgG;
    @DatabaseField
    private  int testpaluG;
    @DatabaseField
    private int paluconfirmeG;
    @DatabaseField
    private  int trG;
    @DatabaseField
    private int diarrheeG;
    @DatabaseField
    private  int vaccinG;

    @DatabaseField
    private  int fa;


    @DatabaseField
    private  int bprougeG;
    @DatabaseField
    private  int bpJauneG;
    @DatabaseField
    private  int bpvert;
    @DatabaseField
    private  int bpvertG;


    @DatabaseField(foreign = true, foreignAutoRefresh = true,maxForeignAutoRefreshLevel = 4)
    private USB usb;
    @DatabaseField
    int syn;
    @DatabaseField
    private String rapportusb;

    @DatabaseField
    private String codeSup;

    @DatabaseField
    private String codeTel;
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

    public Long getIdu() {
        return idu;
    }

    public void setIdu(Long idu) {
        this.idu = idu;
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

    public int getNcgG() {
        return ncgG;
    }

    public void setNcgG(int ncgG) {
        this.ncgG = ncgG;
    }

    public int getTestpaluG() {
        return testpaluG;
    }

    public void setTestpaluG(int testpaluG) {
        this.testpaluG = testpaluG;
    }

    public int getPaluconfirmeG() {
        return paluconfirmeG;
    }

    public void setPaluconfirmeG(int paluconfirmeG) {
        this.paluconfirmeG = paluconfirmeG;
    }

    public int getTrG() {
        return trG;
    }

    public void setTrG(int trG) {
        this.trG = trG;
    }

    public int getDiarrheeG() {
        return diarrheeG;
    }

    public void setDiarrheeG(int diarrheeG) {
        this.diarrheeG = diarrheeG;
    }

    public int getVaccinG() {
        return vaccinG;
    }

    public void setVaccinG(int vaccinG) {
        this.vaccinG = vaccinG;
    }

    public int getFa() {
        return fa;
    }

    public void setFa(int fa) {
        this.fa = fa;
    }

    public void setSyn(int syn) {
        this.syn = syn;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getRapportusb() {
        return rapportusb;
    }

    public void setRapportusb(String rapportusb) {
        this.rapportusb = rapportusb;
    }

    public String getCodeSup() {
        return codeSup;
    }

    public void setCodeSup(String codeSup) {
        this.codeSup = codeSup;
    }

    public String getCodeTel() {
        return codeTel;
    }

    public void setCodeTel(String codeTel) {
        this.codeTel = codeTel;
    }

    public int getBprougeG() {
        return bprougeG;
    }

    public void setBprougeG(int bprougeG) {
        this.bprougeG = bprougeG;
    }

    public int getBpJauneG() {
        return bpJauneG;
    }

    public void setBpJauneG(int bpJauneG) {
        this.bpJauneG = bpJauneG;
    }

    public int getBpvert() {
        return bpvert;
    }

    public void setBpvert(int bpvert) {
        this.bpvert = bpvert;
    }

    public int getBpvertG() {
        return bpvertG;
    }

    public void setBpvertG(int bpvertG) {
        this.bpvertG = bpvertG;
    }
}
