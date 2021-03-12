package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable
public class Medicament {
    @DatabaseField( generatedId = true )
    private int id;
    @DatabaseField
    private String name;
    @ForeignCollectionField(eager=false)
    private ForeignCollection<MedicamentIntrants> medicamentIntrants;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<MedicamentIntrants> getMedicamentIntrants() {
        return medicamentIntrants;
    }

    public void setMedicamentIntrants(ForeignCollection<MedicamentIntrants> medicamentIntrants) {
        this.medicamentIntrants = medicamentIntrants;
    }
}
