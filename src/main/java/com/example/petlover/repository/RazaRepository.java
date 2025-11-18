package com.example.petlover.repository;


import com.example.petlover.domain.mascota.Raza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RazaRepository extends JpaRepository<Raza,Long> {
    Raza findByNombre(String nombre);

}
