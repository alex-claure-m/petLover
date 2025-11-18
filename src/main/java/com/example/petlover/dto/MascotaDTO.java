package com.example.petlover.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class MascotaDTO {
  private String nombre;
  private String tipo;
  private String raza;
  private String sexo;
  private int edad;
  private String fotoUrl;
  private String descripcion;
  private Boolean fichaClinica;

  public String getNombre() {
    return nombre;
  }

  public String getTipo() {
    return tipo;
  }

  public String getRaza() {
    return raza;
  }

  public String getSexo() {
    return sexo;
  }


  public String getDescripcion() {
    return descripcion;
  }

  public Boolean getFichaClinica() {
    return fichaClinica;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public void setRaza(String raza) {
    this.raza = raza;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getFotoUrl() {
    return fotoUrl;
  }

  public void setFotoUrl(String foto) {
    this.fotoUrl = foto;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFichaClinica(Boolean fichaClinica) {
    this.fichaClinica = fichaClinica;
  }
}
