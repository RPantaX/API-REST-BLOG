package com.registro.usuarios.blogapirest.services.impl;

import com.registro.usuarios.blogapirest.dto.PublicacionDTO;
import com.registro.usuarios.blogapirest.dto.PublicacionRespuesta;
import com.registro.usuarios.blogapirest.entities.Publicacion;
import com.registro.usuarios.blogapirest.excepciones.ResourceNotFoundException;
import com.registro.usuarios.blogapirest.repositorires.PublicacionRepository;
import com.registro.usuarios.blogapirest.services.PublicacionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PublicacionRepository publicacionRepository;
    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        //Convertimos de DTO a Entidad
        Publicacion publicacion= convertirAEntidad(publicacionDTO);
        Publicacion nuevaPublicacion= publicacionRepository.save(publicacion);
        //convertimos de entidad a DTO
        return convertirADTO(publicacion);
    }

    @Override
    public PublicacionRespuesta listarPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir ) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();//le indicamos que si pasa un parametro sera de forma ascendente, de lo contraio será de forma descendente.

        Pageable pageable=PageRequest.of(numeroDePagina,medidaDePagina, sort);
        Page<Publicacion> publicacions= publicacionRepository.findAll(pageable);

        List<Publicacion> listaDePublicaciones= publicacions.getContent();
        List<PublicacionDTO> contenido= listaDePublicaciones.stream().map(publicacion -> convertirADTO(publicacion)).collect(Collectors.toList());
        PublicacionRespuesta publicacionRespuesta= new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumeroPagina(publicacions.getNumber());
        publicacionRespuesta.setMedidaPagina(publicacions.getSize());
        publicacionRespuesta.setTotalElementos(publicacions.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicacions.getTotalPages());
        publicacionRespuesta.setUltima(publicacions.isLast()); //confirma si es la última página
        return publicacionRespuesta;
    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(Long id) {
        Publicacion publicacion= publicacionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Publicacion","id", id));
        return convertirADTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion= publicacionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Publicacion","id", id));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        return convertirADTO(publicacionRepository.save(publicacion));
    }

    @Override
    public void eliminarPublicacion(Long id) {
        Publicacion publicacion= publicacionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Publicacion","id", id));
        publicacionRepository.delete(publicacion);
    }

    //CONVERTIR ENTIDAD A DTO
    /*AQUI HACEMOS USO DE ModelMapper PARA AHORRAR MUCHAS LINEAS DE CODIGO, PERO DEJAREMOS COMENTADO LO QUE HABIAMOS HECHO ANTES A MODO DE PRÁCTICA*/
    private PublicacionDTO convertirADTO(Publicacion publicacion){
        /*PublicacionDTO publicacionDTO= new PublicacionDTO();

        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setContenido(publicacion.getContenido());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        */
        PublicacionDTO publicacionDTO= modelMapper.map(publicacion, PublicacionDTO.class);//PublicacionDTO.class: La clase destino a la que se mapearán los datos.
        return publicacionDTO;
    }
    //convertir de DTO a Entidad
    private Publicacion convertirAEntidad(PublicacionDTO publicacionDTO){
        /*Publicacion publicacion= new Publicacion();
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());*/
        Publicacion publicacion= modelMapper.map(publicacionDTO, Publicacion.class);
        return publicacion;
    }
}
