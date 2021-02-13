package com.example.myapp1.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class PriseenCharge {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String  age;
    @DatabaseField
    private  String  sexe;
    @DatabaseField
    private  String  statut;
    @DatabaseField
    private  String  contact;
    @DatabaseField
    private  String refere;
    @DatabaseField
    private  int odeme;
    @DatabaseField
    private  String pec;
    @DatabaseField
    private  String nomaccompagnant;
    @DatabaseField
    private  String enafant;
    @DatabaseField
    private  int MAS;
    @DatabaseField
    private  int PB;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Localite localite;

    @DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd")
    private Date date;
    public PriseenCharge (){};
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public int getOdeme() {
        return odeme;
    }

    public void setOdeme(int odeme) {
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

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public int getPB() {
        return PB;
    }

    public void setPB(int PB) {
        this.PB = PB;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEnafant(String enafant) {
        this.enafant = enafant;
    }

    public void setMAS(int MAS) {
        this.MAS = MAS;
    }

    public String getEnafant() {
        return enafant;
    }

    public int getMAS() {
        return MAS;
    }
}
