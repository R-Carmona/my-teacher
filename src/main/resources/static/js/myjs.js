function verifyPassword() {

    // Ontenemos los valores de los campos de contraseñas 
    pass1 = document.getElementById('password');
    pass2 = document.getElementById('Password');

    // Verificamos si las constraseñas no coinciden
    if (pass1.value != pass2.value) {

        // Si las constraseñas no coinciden mostramos un mensaje
        window.alert("Las Contraseñas deben de coincidir.");
        return false;

    } else {

        window.alert("Registrado en el Sistema.");
        document.test - form2.submit();
        return true;       

    };
}