package com.registro.usuarios.blogapirest.controllers;

import com.registro.usuarios.blogapirest.dto.PublicacionDTO;
import com.registro.usuarios.blogapirest.dto.PublicacionRespuesta;
import com.registro.usuarios.blogapirest.services.PublicacionService;
import com.registro.usuarios.blogapirest.utilerias.AppConstantes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public PublicacionRespuesta listarPublicaciones(
            @RequestParam(value = "pageNo",defaultValue = AppConstantes.NUMERO_PAG_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_PAG_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECC_POR_DEFECTO, required = false) String sortDir){
        return publicacionService.listarPublicaciones(numeroDePagina,medidaDePagina, ordenarPor, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO, @PathVariable("id") Long id){
        return new ResponseEntity<>(publicacionService.actualizarPublicacion(publicacionDTO, id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> actualizarPublicacion(@PathVariable("id") Long id){
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("publicacion exitosamente", HttpStatus.OK);
    }
}
