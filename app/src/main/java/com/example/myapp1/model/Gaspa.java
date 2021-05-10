package com.example.myapp1.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Gaspa {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private Long idu;
    @DatabaseField
    private String mois;
    @DatabaseField
    private String annee;
    @DatabaseField
    private int nbrgaspa;
    @DatabaseField
    private int fe;
    @DatabaseField
    private int fa06r;
    @DatabaseField
    private int fa23r;
    @DatabaseField
    private int fep;
    @DatabaseField
    private int fa06p;
    @DatabaseField
    private int fa23p;
    @DatabaseField
    private int syn;
    @DatabaseField
    private String date;
    @DatabaseField
    private String codeSup;
    @DatabaseField
    private String codeTel;
    @DatabaseField(foreign = true, foreignAutoRefresh = true,maxForeignAutoRefreshLevel = 5)
    private Relais relais;

    public int getNbrgaspa() {
        return nbrgaspa;
    }

    public void setNbrgaspa(int nbrgaspa) {
        this.nbrgaspa = nbrgaspa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getFa06r() {
        return fa06r;
    }

    public void setFa06r(int fa06r) {
        this.fa06r = fa06r;
    }

    public int getFa23r() {
        return fa23r;
    }

    public void setFa23r(int fa23r) {
        this.fa23r = fa23r;
    }

    public int getFep() {
        return fep;
    }

    public void setFep(int fep) {
        this.fep = fep;
    }

    public int getFa06p() {
        return fa06p;
    }

    public void setFa06p(int fa06p) {
        this.fa06p = fa06p;
    }

    public int getFa23p() {
        return fa23p;
    }

    public void setFa23p(int fa23p) {
        this.fa23p = fa23p;
    }

    public Relais getRelais() {
        return relais;
    }

    public void setRelais(Relais relais) {
        this.relais = relais;
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

    public Long getIdu() {
        return idu;
    }

    public void setIdu(Long idu) {
        this.idu = idu;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public int getSyn() {
        return syn;
    }

    public void setSyn(int syn) {
        this.syn = syn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
