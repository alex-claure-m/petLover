package com.example.petlover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

  //necesito levantar el servicio de usuarios ya que el security spring trabajara con esto des del 1er momento
  // el autowired basicamente hace que NO CREE UN OBJETO NUEVO- devuelve una ISNTANCIA ya intectada
  @Autowired
  private UsuarioDetailServiceImp userDetailsService;

  @Autowired
  private PasswordEncod bCryptPasswordEncoder;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    return http
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/","/home","/login",
                "/recuperar-pass",
                "/recuperar/**",
                "/lista",
                "/create-password",
                "/registro-duenio", // este endpoint esta en logincontroller!
                "/petlover/register/**",
                "/petlover/verification/**",
                "/petlover/registropet/**",
                "/petlover/viewpet",
                "/petlover/password/**",
                "/petlover/mascota/**",
                "/petlover/viewpet/**",
                "/petlover/qr/mascota/**",
                "/validation","/index","/css/**","/js/**","/img/**").permitAll()
            .anyRequest().authenticated())
        .csrf(csrf -> csrf.ignoringRequestMatchers("/petlover/mascota/registro", "/","/cloudinary/**"))
        .formLogin(formLogin->formLogin
            .loginPage("/login")
            .failureUrl("/login?error=true")
            .defaultSuccessUrl("/home", false) // una vez loggeado a donde dirigirse?
            .permitAll())
        .logout(logout->logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
            .permitAll())
        .build();
  }

  // los beans son SINGLENTON
  // justamente lo que hace el bean es UTILIZAR la instancia que se creo en la variable: UsuarioDetailServiceImp userDetailsService;
  // y mediante este METODO es exponerlo en SPRING SECURITY encapsulando en un UserDetailsService!
  @Bean
  public UserDetailsService userDetailsService() {
    return userDetailsService;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder.passwordEncoder());
  }

}


