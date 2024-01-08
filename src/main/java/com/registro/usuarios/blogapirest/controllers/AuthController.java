package com.registro.usuarios.blogapirest.controllers;

import com.registro.usuarios.blogapirest.dto.LoginDTO;
import com.registro.usuarios.blogapirest.dto.ResponseDTO;
import com.registro.usuarios.blogapirest.entities.Usuario;
import com.registro.usuarios.blogapirest.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    private ResponseEntity<ResponseDTO> register(@RequestBody Usuario userEntity) throws Exception {
        return new ResponseEntity<>(authService.register(userEntity), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO loginDTO) throws Exception {
        HashMap<String,String> loginHash = authService.login(loginDTO);
        if(loginHash.containsKey("jwt")){
            return new ResponseEntity<>(loginHash, HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(loginHash,HttpStatus.UNAUTHORIZED);
        }
    }
}
