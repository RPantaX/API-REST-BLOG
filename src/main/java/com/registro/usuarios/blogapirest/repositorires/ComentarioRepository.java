package com.registro.usuarios.blogapirest.repositorires;

import com.registro.usuarios.blogapirest.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long> {

    List<Comentario> findByPublicacionId(long publicacionId);
}
