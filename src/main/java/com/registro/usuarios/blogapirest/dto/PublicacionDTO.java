package com.registro.usuarios.blogapirest.dto;

import com.registro.usuarios.blogapirest.entities.Comentario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PublicacionDTO {
    private Long id;
    @NotEmpty //para que no esté vacio
    @Size(min = 2, message = "El titulo de la publicación debería tener al menos 2 caracteres")
    private String titulo;
    @NotEmpty
    @Size(min = 10, message = "La descripción de la publicación debería tener al menos 10 caracteres")
    private String descripcion;
    @NotEmpty
    private String contenido;
    private List<Comentario> comentarios;
}
