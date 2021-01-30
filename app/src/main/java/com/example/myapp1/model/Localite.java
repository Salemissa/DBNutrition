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
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Relais relais;
        @ForeignCollectionField(eager=false)
        private ForeignCollection<Depistage> depistage;
     @ForeignCollectionField(eager=false)
      private ForeignCollection<USB> usb;
        @ForeignCollectionField(eager=false)
        private ForeignCollection<PriseenCharge> priseEnCharges;

    public Localite(){

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

    public Relais getRelais() {
        return relais;
    }

    public void setRelais(Relais relais) {
        this.relais = relais;
    }

    public ForeignCollection<Depistage> getDepistage() {
        return depistage;
    }

    public void setDepistage(ForeignCollection<Depistage> depistage) {
        this.depistage = depistage;
    }

    public ForeignCollection<USB> getUsb() {
        return usb;
    }

    public void setUsb(ForeignCollection<USB> usb) {
        this.usb = usb;
    }

    public ForeignCollection<PriseenCharge> getPriseEnCharges() {
        return priseEnCharges;
    }

    public void setPriseEnCharges(ForeignCollection<PriseenCharge> priseEnCharges) {
        this.priseEnCharges = priseEnCharges;
    }
}
