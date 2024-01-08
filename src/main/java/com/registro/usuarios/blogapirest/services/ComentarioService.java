package com.registro.usuarios.blogapirest.services;

import com.registro.usuarios.blogapirest.dto.ComentarioDTO;
import com.registro.usuarios.blogapirest.entities.Comentario;

import java.util.List;

public interface ComentarioService {
    ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);
    List<ComentarioDTO> listarComentariosPorPublicacionId(Long publicacionId);

    ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId);

    ComentarioDTO ActualizarComentario(Long publicacionId, Long comentarioId,ComentarioDTO solicitudDeComentario);
    void EliminarComentario(Long publicacionId, Long comentarioId);
}
