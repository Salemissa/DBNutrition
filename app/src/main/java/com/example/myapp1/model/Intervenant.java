package com.example.myapp1.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.j256.ormlite.field.DatabaseField;

public abstract class  Intervenant {
  @DatabaseField(generatedId = true)
  private Long id;
  @DatabaseField
  private Long idu;
  @DatabaseField
  private  String nom;
  @DatabaseField
  private  String sexe;
  @DatabaseField
  private String cin;
  @DatabaseField
  @JsonProperty("tel")
  private  String tel;
  @DatabaseField
  private  String statut;
  @DatabaseField
  private String type ;

  @DatabaseField
  private String dateformation ;
  @DatabaseField
  private String formation ;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdu() {
    return idu;
  }

  public void setIdu(Long idu) {
    this.idu = idu;
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


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
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

  public String getDateformation() {
    return dateformation;
  }

  public void setDateformation(String dateformation) {
    this.dateformation = dateformation;
  }

  public String getFormation() {
    return formation;
  }

  public void setFormation(String formation) {
    this.formation = formation;
  }
}
