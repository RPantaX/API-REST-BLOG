package com.registro.usuarios.blogapirest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
    @NotEmpty //para que no esté vacio
    @Size(min = 2, message = "El username debería tener al menos 2 caracteres")
    private String username;
    private String password;

}
