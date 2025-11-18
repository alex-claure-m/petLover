function descargarQR() {
    const qrImg = document.querySelector('.qr-container img');
    const link = document.createElement('a');
    link.href = qrImg.src;
    link.download = 'QR_' + qrImg.alt + '.png';
    link.click();
}