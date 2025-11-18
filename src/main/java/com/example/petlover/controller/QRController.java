package com.example.petlover.controller;

import com.example.petlover.domain.mascota.Mascota;
import com.example.petlover.repository.DuenioRepository;
import com.example.petlover.repository.MascotaRepository;
import com.example.petlover.service.externo.qr.QRCodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/petlover/qr")
public class QRController {

  @Autowired
  private MascotaRepository mascotaRepository;

  @Autowired
  private QRCodeGeneratorService qrgeneratorservice;

  @GetMapping("/mascota/{id}")
  public String generarQRMascota(@PathVariable Long id, Model model){
    Mascota mascota = mascotaRepository.findById(id).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));


    // ahora el contenido que tendra la mascota
    String contenidoQR = String.format(
        "Mascota: %s\nRaza: %s\nSexo: %s\nDueño: %s",
        mascota.getNombre(),
        mascota.getRaza().getNombre(),
        mascota.getSexo(),
        mascota.getDuenio().getNombre()
    );

    //String contenidoQR = "https://www.youtube.com/watch?v=dQw4w9WgXcQ&list=RDdQw4w9WgXcQ&start_radio=1";
    String qrBase64 = qrgeneratorservice.generateQrCodeBase64(contenidoQR, 300, 300);

    // Lo pasás al modelo para mostrarlo en Thymeleaf
    model.addAttribute("qrCode", "data:image/png;base64," + qrBase64);
    model.addAttribute("mascota", mascota);
    return "qr-view";
  }
}
