package com.example.petlover.controller;

import com.example.petlover.domain.duenio.Duenio;
import com.example.petlover.repository.DuenioRepository;
import com.example.petlover.service.negocio.DuenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//se encarga de controlar que la persona que se registro
// se le llegue un mail con un codigo de verificacion, - un token  en si-
@Controller
@RequestMapping("/petlover/verification")
public class VerificationController {

  @Autowired
  private DuenioService duenioService;

  //necesitare el duenioId, y sea pasado entre endpoints, y en este caso solo tomo el duenioId
  @GetMapping("/verificar")
  public String mostrarVerificacion(@RequestParam("duenioId") Long duenioId, Model model) {
    model.addAttribute("duenioId", duenioId);
    return "validationRegister"; // tu plantilla Thymeleaf con los 7 boxes
  }

  //y aca necesito pasarle el duenioId que fue pasado en el Get
  @PostMapping("/verificar")
  public String verificarCodigo(@RequestParam ("duenioId") Long duenioId,
                                @RequestParam("digit1") String d1,
                                @RequestParam("digit2") String d2,
                                @RequestParam("digit3") String d3,
                                @RequestParam("digit4") String d4,
                                @RequestParam("digit5") String d5,
                                @RequestParam("digit6") String d6,
                                Model model
                                ){

    String codigoTotal = d1+d2+d3+d4+d5+d6;
    if( duenioService.validarAlta(duenioId,codigoTotal)){
      model.addAttribute("success","Cuenta Habilitada. Se te Redigira para continuar el proceso");
      //Duenio dueniodto = duenioService.obtenerDuenioPorId(duenioId);
      model.addAttribute("duenioId", duenioId);
      System.out.println("valido la contraseña, ver si redirige");
      return "redirect:/petlover/password/create?duenioId=" +duenioId;
    }else{
      model.addAttribute("error", "Código de verificación incorrecto");
      return "validationRegister"; // Redirige a la página de verificación con un mensaje de error
    }
  }

}
