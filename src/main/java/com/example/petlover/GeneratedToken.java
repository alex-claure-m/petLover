package com.example.petlover;

import java.util.Random;

public class GeneratedToken {
  public String generacionCodigo(){
    Random random = new Random();
    int codigo = random.nextInt(100000);
    String casteoCodigo = String.format("%06d",codigo);
    return casteoCodigo;
  }
}
