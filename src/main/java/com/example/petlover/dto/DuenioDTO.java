package com.example.petlover.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DuenioDTO {
  public Long id;
  public String nombre;
  public String apellido;
  public String email;
  public String nroTelefono;
  public String domicilio;
  public LocalDate fechaNacimiento;
  public Boolean validado = false;
  public List<MascotaDTO> mascotasDTO;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getEmail() {
    return email;
  }

  public String getNroTelefono() {
    return nroTelefono;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public List<MascotaDTO> getMascotasDTO() {
    return mascotasDTO;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setNroTelefono(String nroTelefono) {
    this.nroTelefono = nroTelefono;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public void setValidado(Boolean validado) {
    this.validado = validado;
  }
}
