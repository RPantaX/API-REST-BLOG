package com.registro.usuarios.blogapirest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}),@UniqueConstraint(columnNames = {"username"})})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="nombre", nullable = false)
    private String nombre;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="username", nullable = false)
    private String username;


}
