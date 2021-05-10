package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class PriseenCharge {
    @DatabaseField(generatedId = true)
    @JsonProperty("id")
    private Long id;

    @DatabaseField
    private Long idu;
    @DatabaseField
    private  String mois;
    @DatabaseField
    private String annee;
    @DatabaseField
    @JsonProperty("age")
    private Integer age;

    @DatabaseField
    @JsonProperty("sexe")

    private String sexe;
    @DatabaseField
    @JsonProperty("statut")
    private String statut;
    @DatabaseField
    @JsonProperty("contact")
    private String contact;
    @DatabaseField
    @JsonProperty("refere")
    private String refere;
    @DatabaseField
    @JsonProperty("odeme")
    private String odeme;

    @DatabaseField
    @JsonProperty("pec")
    private String pec;

    @DatabaseField
    @JsonProperty("nomaccompagnant")
    private String nomaccompagnant;

    @DatabaseField
    @JsonProperty("enfant")
    private String enfant;

    @DatabaseField
    @JsonProperty("mas")
    private String mas;

    @DatabaseField
    @JsonProperty("pb")
    private Integer pb;



    @DatabaseField(foreign = true, foreignAutoRefresh = true,maxForeignAutoRefreshLevel = 5)
    @JsonProperty("localite")
    private Localite localite;
    @DatabaseField
    @JsonProperty
    private String date;
    @DatabaseField
    int syn ;
    @DatabaseField
    private String codeSup;
    @DatabaseField
    private String codeTel;
    public PriseenCharge (){};


    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public Long getIdu() {
        return idu;
    }

    public void setIdu(Long idu) {
        this.idu = idu;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRefere() {
        return refere;
    }

    public void setRefere(String refere) {
        this.refere = refere;
    }

    public String getOdeme() {
        return odeme;
    }

    public void setOdeme(String odeme) {
        this.odeme = odeme;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getNomaccompagnant() {
        return nomaccompagnant;
    }

    public void setNomaccompagnant(String nomaccompagnant) {
        this.nomaccompagnant = nomaccompagnant;
    }

    public String getEnfant() {
        return enfant;
    }

    public void setEnfant(String enfant) {
        this.enfant = enfant;
    }

    public String getMas() {
        return mas;
    }

    public void setMas(String mas) {
        this.mas = mas;
    }

    public int getPb() {
        return pb;
    }

    public void setPb(int pb) {
        this.pb = pb;
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

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public String  getDate() {
        return date;
    }



    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PriseenCharge{" +
                "age=" + age +
                ", sexe='" + sexe + '\'' +
                ", statut='" + statut + '\'' +
                ", contact='" + contact + '\'' +
                ", refere='" + refere + '\'' +
                ", odeme=" + odeme +
                ", pec='" + pec + '\'' +
                ", nomaccompagnant='" + nomaccompagnant + '\'' +
                ", enafant='" + enfant + '\'' +
                ", MAS=" + mas +
                ", PB=" + pb +
                ", localite=" + localite.getLocalitename() +
                ", date=" + date +
                '}';
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPb(Integer pb) {
        this.pb = pb;
    }

    public int getSyn() {
        return syn;
    }

    public void setSyn(int syn) {
        this.syn = syn;
    }
}
