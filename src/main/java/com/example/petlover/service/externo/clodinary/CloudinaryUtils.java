package com.example.petlover.service.externo.clodinary;

public class CloudinaryUtils {
  public static String transformarUrlToFront(String url, int width, int height){
    if(!url.contains("/upload")) return url;
    String[] partes = url.split("/upload");
    return partes[0] + "/upload/w_" + width + ",h_" + height + ",c_fill,q_auto,f_auto" +partes[1];
  }
}
