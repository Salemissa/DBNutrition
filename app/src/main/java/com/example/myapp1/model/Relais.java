package com.example.myapp1.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Relais {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private  String nom;
    @DatabaseField
    private String code;
}
