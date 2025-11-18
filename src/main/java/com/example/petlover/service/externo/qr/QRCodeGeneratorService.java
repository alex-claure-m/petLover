package com.example.petlover.service.externo.qr;

public interface QRCodeGeneratorService {
  String generateQrCodeBase64(String qrCodeContentToGenerate, int width, int height);
}
