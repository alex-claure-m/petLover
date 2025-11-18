package com.example.petlover.controller;

import com.example.petlover.domain.mascota.Mascota;
import com.example.petlover.dto.MascotaDTO;
import com.example.petlover.service.negocio.DuenioService;
import com.example.petlover.service.negocio.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/petlover/mascota")
public class MascotaRegisterController {
  @Autowired
  private MascotaService mascotaService;
  @Autowired
  private DuenioService duenioService;

  @PostMapping("/registro")
  public String altaMascota(@ModelAttribute("mascotaDTO") MascotaDTO mascotaDTO, Model model, Principal principalLoggeado){
      try{
        String mailLogeado = principalLoggeado.getName(); // xq el usuario jusatmente es mediante el mail
        // pero tiene sentido que busque al duenio mediante el mail por que de ahi es mas facil traer datos
        //Duenio duenio = duenioService
        //necestio buscar el mail del usuario para setearselo a la mascotaDTO
        // necesito instanciar el cloudinary
        Mascota mascota = mascotaService.altaMascota(mascotaDTO,mailLogeado);
        model.addAttribute("mascota",mascota);
        System.out.println("creador: " + mascota.getFoto());
        return "redirect:/petlover/viewpet/" + mascota.getId(); // una vez que se agrega la mascota , el return es la vista de mascota donde podra generar QR y aceptar!

      }catch(Exception e){
        model.addAttribute("error", "hubo un error a la hora de registrar la mascota");
        System.out.println(e);
        return "registerPet";
      }
  }
}
