package com.registro.usuarios.blogapirest.controllers;

import com.registro.usuarios.blogapirest.dto.ComentarioDTO;
import com.registro.usuarios.blogapirest.services.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;
    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId") Long publicacionId ){
        return comentarioService.listarComentariosPorPublicacionId(publicacionId);
    }
    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "comentarioId") Long comentarioId ){
        return new ResponseEntity<>(comentarioService.obtenerComentarioPorId(publicacionId, comentarioId), HttpStatus.OK) ;
    }
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario( @PathVariable(value = "publicacionId") Long publicacionId,@Valid @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDTO), HttpStatus.CREATED);
    }
    @PutMapping("publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentario( @PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "comentarioId") Long comentarioId,@Valid @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(comentarioService.ActualizarComentario(publicacionId, comentarioId, comentarioDTO), HttpStatus.OK);
    }
    @DeleteMapping("publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "comentarioId") Long comentarioId){
        comentarioService.EliminarComentario(publicacionId, comentarioId);
        return new ResponseEntity<>("Comentario eliminado con Éxito", HttpStatus.OK);
    }
}
