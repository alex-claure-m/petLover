package com.example.petlover.repository;

import com.example.petlover.domain.duenio.Duenio;
import com.example.petlover.domain.duenio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DuenioRepository extends JpaRepository<Duenio,Long > {
  Optional<Duenio> findById(Long id);

  Optional<Duenio> findByEmail(String email);

  Boolean existsByEmail(String email);


  Duenio findByUsuario(Usuario usuario);
}
