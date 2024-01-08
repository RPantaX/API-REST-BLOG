package com.registro.usuarios.blogapirest.services;

import com.registro.usuarios.blogapirest.dto.LoginDTO;
import com.registro.usuarios.blogapirest.dto.ResponseDTO;
import com.registro.usuarios.blogapirest.entities.Usuario;

import java.util.HashMap;

public interface AuthService {
    public HashMap<String, String> login (LoginDTO loginDTO) throws Exception;
    ResponseDTO register(Usuario user) throws Exception;
}
