package com.example.petlover.controller;

import com.example.petlover.domain.mascota.Mascota;
import com.example.petlover.domain.mascota.Raza;
import com.example.petlover.repository.DuenioRepository;
import com.example.petlover.repository.MascotaRepository;
import com.example.petlover.repository.RazaRepository;
import com.example.petlover.service.externo.clodinary.CloudinaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/petlover/viewpet")
public class ViewController {

  @Autowired
  private MascotaRepository mascotaRepository;
  @Autowired
  private DuenioRepository duenioRepository;

  @Autowired
  private RazaRepository raza;
//aca tendra los set para editar mascota - aceptar mascota - generar QR

//aca necesito mediante el mail del dueño, matchear con su mascota y devolver la info de la mascota
  @GetMapping("/{id}")
  public String visualizarMascota(@PathVariable Long id, Model model) throws Exception{
    Mascota mascota = mascotaRepository.findById(id).orElseThrow(()-> new RuntimeException("mascota no encontrada"));

    //String imagenTransformada = CloudinaryUtils.transformarUrlToFront(mascota.getFoto(),300,300);
    //System.out.println("la imagen: " +imagenTransformada);
    model.addAttribute("mascota", mascota);
    //model.addAttribute("imagen",imagenTransformada);
    return "viewPet";
  }


}
