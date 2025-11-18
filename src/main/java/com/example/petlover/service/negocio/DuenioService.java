package com.example.petlover.service.negocio;

import com.example.petlover.GeneratedToken;
import com.example.petlover.domain.duenio.Duenio;
import com.example.petlover.domain.duenio.Usuario;
import com.example.petlover.domain.mascota.Mascota;
import com.example.petlover.dto.DuenioDTO;
import com.example.petlover.dto.MascotaDTO;
import com.example.petlover.repository.DuenioRepository;
import com.example.petlover.repository.MascotaRepository;
import com.example.petlover.repository.UsuarioRepository;
import com.example.petlover.service.externo.email.EmailService;
import com.example.petlover.service.externo.email.models.EmailDetailsDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class DuenioService {
  @Autowired
  private DuenioRepository duenioRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;


  @Autowired
  private EmailService servicioMail;

  @Autowired
  private MascotaRepository mascotaRepository;
  @Autowired
  private MascotaService mascotaService;


  @Transactional
  public Duenio crearDuenio(DuenioDTO dto){
    Duenio duenio = new Duenio();
    Usuario usuario = new Usuario();
    duenio.setNombre(dto.getNombre());
    duenio.setApellido(dto.getApellido());
    duenio.setEmail(dto.getEmail());
    duenio.setDomicilio(dto.getDomicilio());
    duenio.setFechaNacimiento(dto.getFechaNacimiento());
    duenio.setValidado(false);
    usuario.setUsuario(dto.getEmail()); // sera el mismo mail que uso para registrarse -- agregar validaciones!
    usuario.setHabilitado(false);

    GeneratedToken token = new GeneratedToken();
    String clave = token.generacionCodigo();

    duenio.setToken(clave);


    EmailDetailsDTO emailsdetails = new EmailDetailsDTO();
    emailsdetails.setRecipient(duenio.getEmail());
    emailsdetails.setSubject("Verificacion de Cuenta");
    emailsdetails.setMsgBody("Tu codigo de verificacion es: "+ clave +", por favor ingrese este codigo para continuar su registro");
    try{
      servicioMail.sendEmail(emailsdetails);
    }catch(MessagingException e){
      e.printStackTrace();
    }


    dto.setId(duenio.getId());
    duenio.setUsuario(usuario);
    usuario.setDuenio(duenio);
    return duenioRepository.save(duenio);
  }

  @Transactional
  public Duenio actualizarDtatos(Long id, DuenioDTO dto){
    Duenio duenio= duenioRepository.findById(id).orElseThrow(()-> new RuntimeException("Duenio No Encontrado"));
    duenio.setDomicilio(dto.getDomicilio());
    duenio.setNroTelefono(Integer.parseInt(dto.getNroTelefono()));
    return duenioRepository.save(duenio);
  }

  @Transactional
  public boolean validarAlta (Long id, String codigoValidacion){
    Duenio duenio= duenioRepository.findById(id).orElseThrow(()-> new RuntimeException("Duenio No Encontrado"));
    if(duenio.getToken().equals(codigoValidacion)){
      duenio.setValidado(true);
      Usuario usuario = usuarioRepository.findByUsuario(duenio.getEmail());
      usuario.setHabilitado(true);

      duenioRepository.save(duenio);
      usuarioRepository.save(usuario);
      return true;
    }
    return false;

  }

  public Boolean existeCorreo (String email){
    return duenioRepository.existsByEmail(email);
  }

  public Duenio obtenerDuenio(String email){
    return duenioRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("error con obtener duenio de la base de datos"));
  }

  public void actualizarToken(Long duenioid){
    Duenio duenio = duenioRepository.findById(duenioid).orElseThrow(()-> new RuntimeException( " ERROR AL BUSCAR DUENIO"));
    duenio.setToken("0");
    duenioRepository.save(duenio);
  }
  public void enviarToken(Long duenioid){
    Duenio duenio = duenioRepository.findById(duenioid).orElseThrow(()-> new RuntimeException("No encontre el duenio en la base de datos"));
    GeneratedToken token = new GeneratedToken();
    String clave = token.generacionCodigo();

    duenio.setToken(clave);

    EmailDetailsDTO emailsdetails = new EmailDetailsDTO();
    emailsdetails.setRecipient(duenio.getEmail());
    emailsdetails.setSubject("Verificacion de Cuenta");
    emailsdetails.setMsgBody("Tu codigo de verificacion es: "+ clave +", por favor ingrese este codigo para continuar su registro");
    try{
      servicioMail.sendEmail(emailsdetails);
    }catch(MessagingException e){
      e.printStackTrace();
    }
    duenioRepository.save(duenio);
  }
  public Duenio obtenerDuenioPorId (Long duenioid){
    return duenioRepository.findById(duenioid).orElseThrow(()-> new RuntimeException( " error en la busqueda de duenio en l bbdd"));
  }
}
