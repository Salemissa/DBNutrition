package com.example.myapp1.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class MedicamentIntrants {
    @DatabaseField( generatedId = true )
    private int id;
    @DatabaseField( foreignAutoRefresh = true)
    private Structure structure;
    @DatabaseField( foreignAutoRefresh = true)
    private Medicament medicament ;


    int stockinit ;
    int recu ;
    int  quantiteutilisee  ;
    int quantiteperdue ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
