package com.example.petlover.service.externo.clodinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:cloudinary.properties")
public class CloudinaryService {

  private final Cloudinary cloudinary;
  private final String uploadPreset;

  public CloudinaryService(
      @Value("${cloudinary.cloud_name}") String cloudname,
      @Value("${cloudinary.cloud_api_key}") String apikey,
      @Value("${cloudinary.cloud_api_secret}") String apiSecret,
      @Value("${cloudinary.upload_preset}") String uploadPreset
  ) {
    this.cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", cloudname,
        "api_key", apikey,
        "api_secret", apiSecret
    ));
    this.uploadPreset = uploadPreset;
  }

  public Cloudinary getCloudinary() {
    return cloudinary;
  }

  public String getUploadPreset() {
    return uploadPreset;
  }

}
