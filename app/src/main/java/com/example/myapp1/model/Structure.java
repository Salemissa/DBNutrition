package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Structure {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String structurename;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Commune commune;

    @ForeignCollectionField(eager=false)
    private ForeignCollection<Depistage> depistage;


    @ForeignCollectionField(eager=false)
    private ForeignCollection<SuviSousSurvillance> suviSousSurvillances;





    public Structure(){};
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStructurename() {
        return structurename;
    }

    public void setStructurename(String structurename) {
        this.structurename = structurename;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public ForeignCollection<Depistage> getDepistage() {
        return depistage;
    }

    public ForeignCollection<SuviSousSurvillance> getSuviSousSurvillances() {
        return suviSousSurvillances;
    }

    public void setSuviSousSurvillances(ForeignCollection<SuviSousSurvillance> suviSousSurvillances) {
        this.suviSousSurvillances = suviSousSurvillances;
    }

    public void setDepistage(ForeignCollection<Depistage> depistage) {
        this.depistage = depistage;
    }
}
