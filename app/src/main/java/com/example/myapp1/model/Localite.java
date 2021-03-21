package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable
public class Localite {
        @DatabaseField(generatedId = true)
        @JsonProperty("id")
        private Long id;
        @DatabaseField
        @JsonProperty("localitename")
        private String localitename;
        @DatabaseField
        private double lat;
        @DatabaseField
        private double lon;

        @DatabaseField(foreign = true, foreignAutoRefresh = true)
        @JsonProperty("commune")
        private Commune commune;
        @ForeignCollectionField(eager=false)
        @JsonIgnore
        private ForeignCollection<Relais> relais;
        @ForeignCollectionField(eager=false)
        @JsonIgnore
        private ForeignCollection<Depistage> depistage;
        @ForeignCollectionField(eager=false)
        @JsonIgnore
        private ForeignCollection<USB> usb;
        @ForeignCollectionField(eager=false)
        @JsonIgnore
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


    public ForeignCollection<Relais> getRelais() {
        return relais;
    }

    public void setRelais(ForeignCollection<Relais> relais) {
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
