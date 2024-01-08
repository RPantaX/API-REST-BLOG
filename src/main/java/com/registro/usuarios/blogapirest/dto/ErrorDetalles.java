package com.registro.usuarios.blogapirest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetalles {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;
}
