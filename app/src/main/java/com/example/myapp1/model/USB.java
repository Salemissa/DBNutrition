package com.example.myapp1.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class USB {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String usbname;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Localite localite;
    @ForeignCollectionField(eager=false)
    private ForeignCollection<ApprocheCommunataire> approcheCommunataires;
    public  USB(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsbname() {
        return usbname;
    }

    public void setUsbname(String usbname) {
        this.usbname = usbname;
    }

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public ForeignCollection<ApprocheCommunataire> getApprocheCommunataires() {
        return approcheCommunataires;
    }

    public void setApprocheCommunataires(ForeignCollection<ApprocheCommunataire> approcheCommunataires) {
        this.approcheCommunataires = approcheCommunataires;
    }
}
