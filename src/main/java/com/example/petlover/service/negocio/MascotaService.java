package com.example.petlover.service.negocio;

import com.example.petlover.domain.duenio.Duenio;
import com.example.petlover.domain.duenio.Usuario;
import com.example.petlover.domain.mascota.Mascota;
import com.example.petlover.domain.mascota.Raza;
import com.example.petlover.domain.mascota.Sexo;
import com.example.petlover.domain.mascota.Tipo;
import com.example.petlover.dto.MascotaDTO;
import com.example.petlover.repository.DuenioRepository;
import com.example.petlover.repository.MascotaRepository;
import com.example.petlover.repository.RazaRepository;
import com.example.petlover.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class MascotaService {

  @Autowired
  private RazaRepository razaRepository;

  @Autowired
  private MascotaRepository mascotaRepository;

  @Autowired
  private DuenioRepository duenioRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Transactional
  public Mascota altaMascota(MascotaDTO mascotaDTO, String mailDuenio) {
    Mascota mascota = new Mascota();
    mascota.setNombre(mascotaDTO.getNombre());
    mascota.setTipo(Tipo.valueOf(mascotaDTO.getTipo().toUpperCase()));
    mascota.setEdad(mascotaDTO.getEdad());
    mascota.setFichaClinica(mascotaDTO.getFichaClinica());
    Raza raza = new Raza();
 //   Raza raza = razaRepository.findByNombre(mascotaDTO.getRaza()); xq no existe en la bbdd

    raza.setNombre(mascotaDTO.getRaza());
    mascota.setFoto(mascotaDTO.getFotoUrl());
    mascota.setRaza(raza);
    mascota.setSexo(Sexo.valueOf(mascotaDTO.getSexo().toUpperCase()));
    mascota.setFichaClinica(mascotaDTO.getFichaClinica());
    mascota.setDescripcion(mascotaDTO.getDescripcion());

    //trabajar en un mismo service para no crear un bucle
    Duenio duenio = duenioRepository.findByEmail(mailDuenio).orElseThrow(()-> new RuntimeException("error : dueño no encontrado"));
    mascota.setDuenio(duenio);
    duenio.agregarMascotaAlaLista(mascota);

    razaRepository.save(raza);
    return mascotaRepository.save(mascota);
  }

  public List<Mascota> traerMascotas(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    try{
      Usuario usuario = usuarioRepository.findByUsuario(email);
      System.out.println("usuario " + usuario.getUsuario());
      Duenio duenio = duenioRepository.findByUsuario(usuario);
      System.out.println("duenio " + duenio.getNombre());
      List <Mascota> mascotas = mascotaRepository.findByDuenio(duenio);
      System.out.println("cantidadMascotas " + mascotas.stream().count());
      return mascotas;
    }
    catch( Exception e){
      throw new RuntimeException("error a la hora de traer las masctoas"+ e.getMessage());
    }

  }
}
