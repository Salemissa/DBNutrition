package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class MedicamentIntrants {
    @DatabaseField( generatedId = true )
    private Long id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Medicament medicament ;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Structure structure;
    @DatabaseField
    int stockinit ;
    @DatabaseField
    int recu ;
    @DatabaseField
    int  quantiteutilisee  ;
    @DatabaseField
    int quantiteperdue ;

    @DatabaseField
    private String date;
    @DatabaseField
    int syn ;

    @DatabaseField
    private String mois;
    @DatabaseField
   private String annee;
    public  MedicamentIntrants(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public int getStockinit() {
        return stockinit;
    }

    public void setStockinit(int stockinit) {
        this.stockinit = stockinit;
    }

    public int getRecu() {
        return recu;
    }

    public void setRecu(int recu) {
        this.recu = recu;
    }

    public int getQuantiteutilisee() {
        return quantiteutilisee;
    }

    public void setQuantiteutilisee(int quantiteutilisee) {
        this.quantiteutilisee = quantiteutilisee;
    }

    public int getQuantiteperdue() {
        return quantiteperdue;
    }
    public void setQuantiteperdue(int quantiteperdue) {
        this.quantiteperdue = quantiteperdue;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSyn() {
        return syn;
    }

    public void setSyn(int syn) {
        this.syn = syn;
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

    @Override
    public String toString() {
        return "MedicamentIntrants{" +
                "medicament=" + medicament.getName() +
                ", structure=" + structure.getStructurename() +
                ", stockinit=" + stockinit +
                ", recu=" + recu +
                ", quantiteutilisee=" + quantiteutilisee +
                ", quantiteperdue=" + quantiteperdue +
                ", date='" + date + '\'' +
                ", syn=" + syn +
                '}';
    }
}
