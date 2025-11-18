package com.example.petlover.controller;

import com.example.petlover.domain.duenio.Duenio;
import com.example.petlover.dto.DuenioDTO;
import com.example.petlover.repository.DuenioRepository;
import com.example.petlover.service.negocio.DuenioService;
import com.example.petlover.service.negocio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/petlover/register")
public class RegisterController {
  @Autowired
  private DuenioService dueniobd;

  @Autowired
  private UsuarioService usuariodb;

  //recibir datos
  @PostMapping("/registro-duenio")
  public String altaDuenio(@ModelAttribute("duenioDTO") DuenioDTO dto, Model model, SessionStatus sessionStatus){
    System.out.println("DTO recibido: " + dto.getNombre()+ "," + dto.getDomicilio()); // debug
    try {
     Duenio dueniodto = dueniobd.crearDuenio(dto);
  // validar o buscar que el correo ya este registrado o usado, y refrescar la pagina para que escriba otro
      // o validar datos tambien, pero no puse dni, asi que ggwp
      return "redirect:/petlover/verification/verificar?duenioId=" + dueniodto.getId();
    }catch(Exception e){
      model.addAttribute("error","hubo un error al registrar el duenio"+ e.getMessage());
      e.printStackTrace();
      return "register";
    }
  }

}
