package com.registro.usuarios.blogapirest.services.impl;

import com.registro.usuarios.blogapirest.dto.ComentarioDTO;
import com.registro.usuarios.blogapirest.entities.Comentario;
import com.registro.usuarios.blogapirest.entities.Publicacion;
import com.registro.usuarios.blogapirest.excepciones.BlogAppException;
import com.registro.usuarios.blogapirest.excepciones.ResourceNotFoundException;
import com.registro.usuarios.blogapirest.repositorires.ComentarioRepository;
import com.registro.usuarios.blogapirest.repositorires.PublicacionRepository;
import com.registro.usuarios.blogapirest.services.ComentarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;
    @Override
    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario= mappearAEntity(comentarioDTO);
        Publicacion publicacion= publicacionRepository.findById(publicacionId).orElseThrow(()->new ResourceNotFoundException("Publicacion", "id", publicacionId));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario= comentarioRepository.save(comentario);
        return mappearADTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDTO> listarComentariosPorPublicacionId(Long publicacionId) {
        List<Comentario> comentarios=comentarioRepository.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mappearADTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId) {
        Publicacion publicacion= publicacionRepository.findById(publicacionId)
                .orElseThrow(()->new ResourceNotFoundException("Publicacion", "id", publicacionId));
        Comentario comentario= comentarioRepository.findById(comentarioId)
                .orElseThrow(()->new ResourceNotFoundException("Comentario", "id", comentarioId));

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación.");
        }else{
            return mappearADTO(comentario);
        }
    }

    @Override
    public ComentarioDTO ActualizarComentario(Long publicacionId,Long comentarioId ,ComentarioDTO solicitudDeComentario) {
        Publicacion publicacion= publicacionRepository.findById(publicacionId)
                .orElseThrow(()->new ResourceNotFoundException("Publicacion", "id", publicacionId));
        Comentario comentario= comentarioRepository.findById(comentarioId)
                .orElseThrow(()->new ResourceNotFoundException("Comentario", "id", comentarioId));
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación.");
        }
        comentario.setNombre(solicitudDeComentario.getNombre());
        comentario.setEmail(solicitudDeComentario.getEmail());
        comentario.setCuerpo(solicitudDeComentario.getCuerpo());

        Comentario comentarioActualizado= comentarioRepository.save(comentario);
        return mappearADTO(comentarioActualizado);
    }

    @Override
    public void EliminarComentario(Long publicacionId, Long comentarioId) {
        Publicacion publicacion= publicacionRepository.findById(publicacionId)
                .orElseThrow(()->new ResourceNotFoundException("Publicacion", "id", publicacionId));
        Comentario comentario= comentarioRepository.findById(comentarioId)
                .orElseThrow(()->new ResourceNotFoundException("Comentario", "id", comentarioId));
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación.");
        }
        comentarioRepository.delete(comentario);
    }


    private ComentarioDTO mappearADTO(Comentario comentario){
        /*ComentarioDTO comentarioDTO= new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setNombre(comentario.getNombre());
        comentarioDTO.setCuerpo(comentario.getCuerpo());
        comentarioDTO.setEmail(comentario.getEmail());*/
        ComentarioDTO comentarioDTO= modelMapper.map(comentario, ComentarioDTO.class);
        return comentarioDTO;
    }
    private Comentario mappearAEntity(ComentarioDTO comentarioDTO){
        /*Comentario comentario= new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        comentario.setEmail(comentarioDTO.getEmail());*/
        Comentario comentario= modelMapper.map(comentarioDTO, Comentario.class);
        return comentario;
    }
}
