package com.example.petlover.repository;

import com.example.petlover.domain.duenio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
  Optional<Usuario> findById(Long id); // revisar por hay un datazo que no concientice

  Usuario findByUsuario(String user);

  //Usuario findByEmail(String email);

  Usuario findByDuenio_Id(Long id);
  //el findxxx_id , es como un buscame el duenio POR esto o aquello (en este caso un long id)



}
