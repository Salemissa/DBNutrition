package com.example.myapp1.model;

import com.j256.ormlite.field.DatabaseField;

public abstract class  Intervenant {
  @DatabaseField(generatedId = true)
  private Long id;
  @DatabaseField
  private  String nom;
  @DatabaseField
  private  String sexe;
  @DatabaseField
  private String cin;
  @DatabaseField
  private  String telephone;
  @DatabaseField
  private  String statut;
  @DatabaseField
  private String type ;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }



  public String getSexe() {
    return sexe;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setSexe(String sexe) {
    this.sexe = sexe;
  }

  public String getCin() {
    return cin;
  }

  public void setCin(String cin) {
    this.cin = cin;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getStatut() {
    return statut;
  }

  public void setStatut(String statut) {
    this.statut = statut;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
