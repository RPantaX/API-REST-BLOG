package com.registro.usuarios.blogapirest.repositorires;

import com.registro.usuarios.blogapirest.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT *FROM usuarios WHERE username= :username", nativeQuery = true)
    Optional<Usuario> findByUsuername(String username);


}
