package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable
public class Localite {


        @DatabaseField(generatedId = true)
        private Long id;
        @DatabaseField
        private String localitename;
        @DatabaseField(foreign = true, foreignAutoRefresh = true)
        private Commune commune;
   // @ForeignCollectionField(eager=false)
    //private ForeignCollection<PriseenCharge> priseEnCharges;

    Localite(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalitename() {
        return localitename;
    }

    public void setLocalitename(String localitename) {
        this.localitename = localitename;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }
}
