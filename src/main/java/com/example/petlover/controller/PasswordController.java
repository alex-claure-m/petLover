package com.example.petlover.controller;

import com.example.petlover.domain.duenio.Duenio;
import com.example.petlover.domain.duenio.Usuario;
import com.example.petlover.repository.UsuarioRepository;
import com.example.petlover.service.negocio.DuenioService;
import com.example.petlover.service.negocio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/petlover/password")
public class PasswordController {

  @Autowired
  private DuenioService duenioservice;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping("/create")
  public String create(@RequestParam("duenioId") Long duenioId, Model model){
    Duenio duenio = duenioservice.obtenerDuenioPorId(duenioId);
    model.addAttribute("duenioId", duenioId);
    model.addAttribute("usuario",duenio.getEmail());
    return "createPassword";
  }
  @PostMapping("/create")
  public String altaPassword(@RequestParam Long duenioId, @RequestParam String password, @RequestParam String confirmPassword, Model model){
    Usuario usuario = usuarioRepository.findByDuenio_Id(duenioId);

    if (usuario == null) {
      model.addAttribute("error", "No se encontró el usuario asociado al dueño.");
      return "createPassword";
    }

    if (!password.equals(confirmPassword)) {
      model.addAttribute("error", "Las contraseñas no coinciden.");
      model.addAttribute("duenioId", duenioId);
      model.addAttribute("usuario", usuario.getUsuario());
      return "createPassword";
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    usuario.setPassword(encoder.encode(password));
    usuarioRepository.save(usuario);

    model.addAttribute("success", "Contraseña creada con éxito. Ahora podés iniciar sesión.");
    return "login";
  }
}
