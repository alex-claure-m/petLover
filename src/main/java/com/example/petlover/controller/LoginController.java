package com.example.petlover.controller;

import com.example.petlover.dto.DuenioDTO;
import com.example.petlover.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
  // recorda que el autowired es para instanciar sin necesidad de hacer un CONSTURCTOR
  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping("/login")
  public String login(@RequestParam(name="error", required=false) String error, Model model){
    if(error != null){
      model.addAttribute("error","Nombre de usuario o contraseña incorrecta/s");
    }
    return "login";
  }

  //necesito hacer el endpoint donde el back entendera a donde debe apunntar
  // dentro del endpoint tendra la funcion de crear una vista que es RegistroDuenio.html

  @GetMapping("/registro-duenio")
  public ModelAndView register() {
    //estoy guardando un modelo y vista llamado "registroDuenio" -> el mismo que el html para que luego lo guarde
    ModelAndView modelAndView = new ModelAndView("register");
    modelAndView.addObject("duenioDTO", new DuenioDTO());
    return modelAndView;
  }

  @GetMapping("/recuperar-pass")
  public ModelAndView recuperarCredencial(){
    ModelAndView modelAndView = new ModelAndView("forgot");
    return modelAndView;
  }
}
