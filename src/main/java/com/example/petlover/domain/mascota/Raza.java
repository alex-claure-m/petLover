package com.example.petlover.domain.mascota;

import com.example.petlover.domain.PersistenceId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

// esto es por que si declaro enum , lueo para separarlo es un quilombo
// ademas Raza se acoplaria con cada mascota
@Getter
@Setter
@Entity
public class Raza extends PersistenceId {
  private String nombre;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
