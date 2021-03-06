package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class MedicamentIntrants {
    @DatabaseField( generatedId = true )
    private Long id;

    @DatabaseField
    private Long idu;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Medicament medicament ;
    @DatabaseField(foreign = true, foreignAutoRefresh = true,maxForeignAutoRefreshLevel = 4)
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
    @DatabaseField
    private String codeSup;
    @DatabaseField
    private String codeTel;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    @JsonIgnore
    private byte[] rapport;
    @DatabaseField
    private String rapportIntrant;
    @DatabaseField
    private String dateRapport;


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

    public Long getIdu() {
        return idu;
    }

    public void setIdu(Long idu) {
        this.idu = idu;
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

    public String getRapportIntrant() {
        return rapportIntrant;
    }

    public void setRapportIntrant(String rapportIntrant) {
        this.rapportIntrant = rapportIntrant;
    }

    public String getDateRapport() {
        return dateRapport;
    }

    public void setDateRapport(String dateRapport) {
        this.dateRapport = dateRapport;
    }

    public byte[] getRapport() {
        return rapport;
    }

    public void setRapport(byte[] rapport) {
        this.rapport = rapport;
    }

    @Override
    public String toString() {
        return "MedicamentIntrants{" +
                "medicament=" + medicament.getNom() +
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
