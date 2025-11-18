package com.example.petlover.repository;

import com.example.petlover.domain.duenio.Duenio;
import com.example.petlover.domain.mascota.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MascotaRepository extends JpaRepository<Mascota,Long> {
  Optional<Mascota> findById(Long id);
  Mascota findByNombre(String nombre);
  List<Mascota> findByDuenio(Duenio duenio);

}
