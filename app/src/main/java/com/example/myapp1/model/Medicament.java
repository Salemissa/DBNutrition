package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable
public class Medicament {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String nom;
    @ForeignCollectionField(eager=false)
    private ForeignCollection<MedicamentIntrants> medicamentIntrants;


    public Medicament(){}
    public  Medicament(String name){
        this.nom=name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ForeignCollection<MedicamentIntrants> getMedicamentIntrants() {
        return medicamentIntrants;
    }

    public void setMedicamentIntrants(ForeignCollection<MedicamentIntrants> medicamentIntrants) {
        this.medicamentIntrants = medicamentIntrants;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
