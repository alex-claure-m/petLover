function togglePassword() {
    const passInput = document.getElementById("password");
    const toggleBtn = document.querySelector(".toggle-pass");

    const isHidden = passInput.type === "password";
    passInput.type = isHidden ? "text" : "password";
    toggleBtn.textContent = isHidden ? "🙈" : "👁️";
    toggleBtn.setAttribute("aria-label", isHidden ? "Ocultar contraseña" : "Mostrar contraseña");

    // ✅ Esto mantiene el foco (evita que se "desenfoque" el input)
    passInput.focus();
}
