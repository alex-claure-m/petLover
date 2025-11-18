package com.example.petlover.controller;

import com.example.petlover.domain.duenio.Usuario;
import com.example.petlover.domain.mascota.Mascota;
import com.example.petlover.dto.MascotaDTO;
import com.example.petlover.repository.UsuarioRepository;
import com.example.petlover.service.externo.clodinary.CloudinaryService;
import com.example.petlover.service.negocio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private MascotaService mascotaService;
  @Autowired
  private CloudinaryService cloudinaryService;

  @GetMapping({"/","/home"})
  public String home(Model model, Usuario usuario){
    if(usuario !=null){
      model.addAttribute("username", usuario.getUsuario());
    }
    return "home";
  }
  @GetMapping("/registrarmascota")
  public ModelAndView registro(){
    ModelAndView model = new ModelAndView("registerPet");
    model.addObject("mascotaDTO", new MascotaDTO());
    model.addObject("cloudName", cloudinaryService.getCloudinary().config.cloudName);
    //System.out.println("cldname: " + cloudinaryService.getCloudinary().config.cloudName );
    model.addObject("uploadPreset", cloudinaryService.getUploadPreset());
    return model;
  }

  @GetMapping("/lista")
  public ModelAndView listaMascotas(){
    List<Mascota> mascotas = mascotaService.traerMascotas();
    ModelAndView model = new ModelAndView("listPet");
    model.addObject("mascotas", mascotas);
    return model;
  }

}
