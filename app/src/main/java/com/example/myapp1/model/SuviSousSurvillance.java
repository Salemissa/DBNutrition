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
    private Long idu;
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
    private  int rea;
    @DatabaseField
    private int  gueris;
    @DatabaseField
    private int deces;
    @DatabaseField
    private int abonde;
    @DatabaseField
    private int nonRep;
    @DatabaseField
    private int autrecas;
    @DatabaseField
    private int refCRENI;
    @DatabaseField
    private int transCRENAS;
    @DatabaseField
    private String date;

    @DatabaseField
    private int syn;

    @DatabaseField
    private String codeSup;
    @DatabaseField
    private String codeTel;

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

    public int getAutrecas() {
        return autrecas;
    }

    public void setAutrecas(int autrecas) {
        this.autrecas = autrecas;
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

    public Long getIdu() {
        return idu;
    }

    public void setIdu(Long idu) {
        this.idu = idu;
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

    public int getGueris() {
        return gueris;
    }

    public void setGueris(int gueris) {
        this.gueris = gueris;
    }

    public int getDeces() {
        return deces;
    }

    public void setDeces(int deces) {

        this.deces = deces;
    }

    public int getAbonde() {
        return abonde;
    }

    public void setAbonde(int abonde) {

        this.abonde = abonde;
    }

    public int getNonRep() {
        return nonRep;
    }

    public void setNonRep(int nonRep) {
        this.nonRep = nonRep;
    }

    public int getRefCRENI() {
        return refCRENI;
    }

    public void setRefCRENI(int refCRENI)
    {
        this.refCRENI = refCRENI;
    }

    public int getTransCRENAS() {
        return transCRENAS;
    }

    public void setTransCRENAS(int transCRENAS) {

        this.transCRENAS = transCRENAS;
    }

    public int getSsdebuit() {
        return ssdebuit;
    }

    public void setSsdebuit(int ssdebuit) {
        this.ssdebuit = ssdebuit;
    }

    public  String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNcg() {
        return ncg;
    }

    public void setNcg(int ncg) {
        this.ncg = ncg;
    }

    public int getNgf() {
        return ngf;
    }

    public void setNgf(int ngf) {
        this.ngf = ngf;
    }

    public int getRea() {
        return rea;
    }

    public void setRea(int rea) {
        this.rea = rea;
    }

    public int getSyn() {
        return syn;
    }

    public void setSyn(int syn) {
        this.syn = syn;
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
