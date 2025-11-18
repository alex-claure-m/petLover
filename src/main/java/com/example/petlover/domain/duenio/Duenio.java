package com.example.petlover.domain.duenio;

import com.example.petlover.domain.PersistenceId;
import com.example.petlover.domain.mascota.Mascota;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Duenio extends PersistenceId {

  private String nombre;
  private String apellido;
  private String domicilio;
  private LocalDate fechaNacimiento;
  private String email;
  private int nroTelefono;
  private Boolean validado;
  private String token;

  // el mappedby para mappear este ATRIBUTO usuario , defino ademas la bidireccionalidad
  // osea que duenio conoce al usuario y usuario al dueño
  // basicamente digo: che: yo atributo usuario, estoy relacionado con el atributo duenio de la clase Usuario!
  // en esta columna ira la relacion
  @OneToOne(mappedBy="duenio", cascade = CascadeType.ALL)
  private Usuario usuario;

  // aca pasa algo similar, por que bueno, sera bidireccional, osea que habra una columna para cada uno, en donde DUENIO conoce
  // a las mascotas y las mascotas deberan conocer a su DUEÑIO (ya que es promordial para el qr)
  // unDuenio - to - MuchasMascotas
  // mascotas debe ser mapeada con el atributo duenio (perteneciente a la clase Mascota en su atributo duenio)
  @OneToMany(mappedBy = "duenio",cascade = CascadeType.ALL)
  private List<Mascota> mascotas = new ArrayList<>();


  public void agregarMascota(Mascota unaMascota){
    this.mascotas.add(unaMascota);
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setNroTelefono(int nroTelefono) {
    this.nroTelefono = nroTelefono;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public String getEmail() {
    return email;
  }

  public int getNroTelefono() {
    return nroTelefono;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public List<Mascota> getMascotas() {
    return mascotas;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public Boolean getValidado() {
    return validado;
  }

  public void setValidado(Boolean validado) {
    this.validado = validado;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public void agregarMascotaAlaLista(Mascota mascota){
    this.mascotas.add(mascota);
  }


}
