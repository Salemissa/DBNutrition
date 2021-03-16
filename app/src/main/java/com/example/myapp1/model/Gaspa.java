package com.example.myapp1.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Gaspa {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private int fe;
    @DatabaseField
    private int fa06r;
    @DatabaseField
    private int fa23r;
    @DatabaseField
    private int fep;
    @DatabaseField
    private int fa06p;
    @DatabaseField
    private int fa23p;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Relais relais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getFa06r() {
        return fa06r;
    }

    public void setFa06r(int fa06r) {
        this.fa06r = fa06r;
    }

    public int getFa23r() {
        return fa23r;
    }

    public void setFa23r(int fa23r) {
        this.fa23r = fa23r;
    }

    public int getFep() {
        return fep;
    }

    public void setFep(int fep) {
        this.fep = fep;
    }

    public int getFa06p() {
        return fa06p;
    }

    public void setFa06p(int fa06p) {
        this.fa06p = fa06p;
    }

    public int getFa23p() {
        return fa23p;
    }

    public void setFa23p(int fa23p) {
        this.fa23p = fa23p;
    }

    public Relais getRelais() {
        return relais;
    }

    public void setRelais(Relais relais) {
        this.relais = relais;
    }


}
