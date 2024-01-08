package com.registro.usuarios.blogapirest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY) //carga perezosa, listar solo cuando lo necesitemos
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;
}
