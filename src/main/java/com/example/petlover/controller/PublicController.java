package com.example.petlover.controller;

import com.example.petlover.domain.mascota.Mascota;
import com.example.petlover.repository.MascotaRepository;
import com.example.petlover.service.externo.clodinary.CloudinaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//este es el controlador para poder visualizar de manera publica al animal

@Controller
@RequestMapping("/public")
public class PublicController {

  @Autowired
  private MascotaRepository mascotaRepository;

  @GetMapping("/mascota/{id}")
  public String verMascotaQr(@PathVariable Long id, Model model){
    Mascota mascota = mascotaRepository.findById(id).orElseThrow(()-> new RuntimeException( "Mascota no encontradas"));
    String imagenTransformada = CloudinaryUtils.transformarUrlToFront(mascota.getFoto(),300,300);
    model.addAttribute("imagen",imagenTransformada);
    model.addAttribute("mascota",mascota);
    return "view-public";
  }
}
