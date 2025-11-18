package com.example.petlover;

import com.example.petlover.domain.duenio.Usuario;
import com.example.petlover.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailServiceImp implements UserDetailsService {
  // me habia olvidado, justamamente como EN SPRINGSECURITY , necesito siempre validar controles
  // necesito el repository que es donde consultara siempre a la base de datos
  // ademas implementara UserDetailsService que un servicio propio de Spring
  // facilitara a la hora de los metodos para traer de la bbdd

  //yo solo necesito "levantar" el UsuarioRepository que es el que tendra las funcionalidades con la bbdd
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByUsuario(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
    //return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    return User.withUsername(usuario.getUsuario())
        .password(usuario.getPassword())
        .build();
  }


}
