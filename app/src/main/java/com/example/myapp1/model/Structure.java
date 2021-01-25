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
    private ForeignCollection<DepistagePassif> depistagePassifs;



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

    public ForeignCollection<DepistagePassif> getDepistagePassifs() {
        return depistagePassifs;
    }

    public void setDepistagePassifs(ForeignCollection<DepistagePassif> depistagePassifs) {
        this.depistagePassifs = depistagePassifs;
    }
}
