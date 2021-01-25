package com.example.myapp1.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class USB {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String usbname;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Localite localite;
}
