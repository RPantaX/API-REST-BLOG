package com.registro.usuarios.blogapirest.services;

import com.registro.usuarios.blogapirest.dto.PublicacionDTO;
import com.registro.usuarios.blogapirest.dto.PublicacionRespuesta;
import com.registro.usuarios.blogapirest.entities.Publicacion;

import java.util.List;

public interface PublicacionService {
    PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    PublicacionRespuesta listarPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
    PublicacionDTO obtenerPublicacionPorId(Long id);
    PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO,Long id);
    void eliminarPublicacion(Long id);
}
