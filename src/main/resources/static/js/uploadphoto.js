 document.addEventListener("DOMContentLoaded", async () => {
    renderUploadWidget();
});

    // upload
    const uploadOptions = {
    cloudName: cloudName,
    uploadPreset: uploadPreset,
    multiple: false, // para evitar que suba fotos multiples
    cropping: true,
    folder: "mascotas", // para subir en el folder en cloudinary
};

    const processResults = (error, result) => {
    if (!error && result && result.event === "success") {
    console.log(result);
    // if successful renders to page
    document.querySelector("#uploaded").src = result.info.secure_url;
    document.querySelector("#uploaded").style.display = "block"; // bloquea cuando se sube la foto para que no se permita subir mas fotos
    document.querySelector("#fotoUrl").value = result.info.secure_url; // tomo la url qe generara cloudinary
}
};

    const renderUploadWidget = () => {
    const myWidget = window.cloudinary.createUploadWidget(
    uploadOptions,
    processResults
    );
    document
    .getElementById("upload_widget")
    .addEventListener("click", () => myWidget.open(), false);
};
