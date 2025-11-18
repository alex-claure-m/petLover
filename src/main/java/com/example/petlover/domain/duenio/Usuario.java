package com.example.petlover.domain.duenio;

import com.example.petlover.domain.PersistenceId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario  extends PersistenceId {
  private String usuario;
  private String password;
  private boolean habilitado;

  // aca el atributo duenio, es OneToOne
  // y le agrego una columna que justamente sea duenio_id (que crea automaticamente springboot cuando hay nuevo usuarios creados)
  @OneToOne
  @JoinColumn(name="duenio_id")
  private Duenio duenio;

  public String getUsuario() {
    return usuario;
  }

  public String getPassword() {
    return password;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  public void setHabilitado(boolean habilitado) {
    this.habilitado = habilitado;
  }

  public boolean getHabilitado() {
    return habilitado;
  }

  public void setDuenio(Duenio duenio) {
    this.duenio = duenio;
  }
}
