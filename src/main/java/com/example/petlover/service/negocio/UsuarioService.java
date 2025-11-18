package com.example.petlover.service.negocio;

import com.example.petlover.domain.duenio.Usuario;
import com.example.petlover.dto.UsuarioDTO;
import com.example.petlover.repository.DuenioRepository;
import com.example.petlover.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private DuenioRepository duenioRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public Usuario altaUsuario(String email, String password){
    Usuario usuario = usuarioRepository.findByUsuario(email);
    if(usuario.getHabilitado()){
      usuario.setPassword(passwordEncoder.encode(password));
      usuarioRepository.save(usuario);
    }
    else{
      throw new RuntimeException("El usuario no esta habilitado");
    }
    return usuario;
  }
}
