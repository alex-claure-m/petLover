package com.example.petlover.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
  public String usuario;
  public String password;
  public Boolean habilitado;
}
