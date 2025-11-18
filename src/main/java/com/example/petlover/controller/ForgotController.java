package com.example.petlover.controller;

import com.example.petlover.domain.duenio.Duenio;
import com.example.petlover.service.negocio.DuenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recuperar")
public class ForgotController {

  @Autowired
  private DuenioService duenioServicio;

  @PostMapping("/resend-email")
  public String reenvioEmail(@RequestParam("email") String correo, Model model){
      if(existeDuenioCon(correo)){
        Duenio duenio = duenioServicio.obtenerDuenio(correo);
        model.addAttribute("success", "correo en nuestra base de datos.. enviando token");
        duenioServicio.actualizarToken(duenio.getId());
        duenioServicio.enviarToken(duenio.getId());
        return "redirect:/petlover/verification/verificar?duenioId=" + duenio.getId();
      }
      else{
        System.out.println("hubo error");
        model.addAttribute("error", "correo no encontrado en nuestra banco de datos. redirigiendo");
        return "redirect:/login";
      }
  }

  //tiene que ir en servicios
  public Boolean existeDuenioCon(String correo){
    return duenioServicio.existeCorreo(correo);
  }
}
