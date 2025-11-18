package com.example.petlover.domain.mascota;

import com.example.petlover.domain.PersistenceId;
import com.example.petlover.domain.duenio.Duenio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Mascota extends PersistenceId {
  private String nombre;
  @Enumerated(EnumType.STRING)
  private Tipo tipo;
  @OneToOne
  private Raza raza;
  private int edad;

  @Enumerated(EnumType.STRING)
  private Sexo sexo;

  // duenio, es el atributo que dira quien es el propietario de la mascota.
  // y como la relacion es ManyToOne - un dueño puede tener muchas mascotas
  //  y le asigno en una columna nueva "duenio_id"
  @ManyToOne
  @JoinColumn(name="duenio_id")
  private Duenio duenio;

  private String foto;
  private String descripcion;

  private Boolean fichaClinica;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

  public Raza getRaza() {
    return raza;
  }

  public void setRaza(Raza raza) {
    this.raza = raza;
  }

  public Sexo getSexo() {
    return sexo;
  }

  public void setSexo(Sexo sexo) {
    this.sexo = sexo;
  }

  public Duenio getDuenio() {
    return duenio;
  }

  public void setDuenio(Duenio duenio) {
    this.duenio = duenio;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Boolean getFichaClinica() {
    return fichaClinica;
  }

  public void setFichaClinica(Boolean fichaClinica) {
    this.fichaClinica = fichaClinica;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }
}
