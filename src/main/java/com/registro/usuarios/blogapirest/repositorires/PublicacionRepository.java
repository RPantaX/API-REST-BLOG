package com.registro.usuarios.blogapirest.repositorires;

import com.registro.usuarios.blogapirest.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}
