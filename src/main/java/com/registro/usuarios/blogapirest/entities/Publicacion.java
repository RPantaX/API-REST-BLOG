package com.registro.usuarios.blogapirest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publicaciones", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})})
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", nullable = false)
    private String titulo;

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="contenido", nullable = false)
    private String contenido;

    @JsonBackReference/*ignora la serialización, evitamos el error de json*/
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL,orphanRemoval = true) //orphanRemoval: cada que eliminemos un valor los demás objetos asociados a él tambien se eliminarán.
    private List<Comentario> comentarios= new ArrayList<>();

}
