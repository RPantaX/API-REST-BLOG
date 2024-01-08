package com.registro.usuarios.blogapirest.services.impl;

import com.registro.usuarios.blogapirest.dto.LoginDTO;
import com.registro.usuarios.blogapirest.dto.ResponseDTO;
import com.registro.usuarios.blogapirest.entities.Usuario;
import com.registro.usuarios.blogapirest.repositorires.UsuarioRepository;
import com.registro.usuarios.blogapirest.services.AuthService;
import com.registro.usuarios.blogapirest.services.JWTUtilityService;
import com.registro.usuarios.blogapirest.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JWTUtilityService jwtUtilityService;
    @Autowired
    private UserValidation userValidation;

    @Override
    public HashMap<String, String> login(LoginDTO loginDTO) throws Exception {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<Usuario> user= usuarioRepository.findByUsuername(loginDTO.getUsername());
            if(user.isEmpty()){
                jwt.put("error","User not registered");
                return jwt;
            }
            //verificar la contraseña
            if(verifyPassword(loginDTO.getPassword(), user.get().getPassword())){
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
            } else{
                jwt.put("error","Authentication failed");
            }
            return jwt;
        }catch (Exception e){
            throw new Exception(e.toString());
        }
    }
    @Override
    public ResponseDTO register(Usuario user) throws Exception{
        try{
            ResponseDTO responseDTO= userValidation.validate(user);
            if(responseDTO.getNumOfErrors()>0){
                return responseDTO;
            }
            List<Usuario> getAllUsers= usuarioRepository.findAll();
            for(Usuario repearFields : getAllUsers){
                if(repearFields!=null){
                    responseDTO.setNumOfErrors(1);
                    responseDTO.setMessage("User already exists!");
                    return responseDTO;
                }
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);//encriptamos la contraseña
            user.setPassword(encoder.encode(user.getPassword())); //y se ña asignamos al usuario
            usuarioRepository.save(user);
            responseDTO.setMessage("User created succesfully!");

            return responseDTO;
        }catch (Exception e){
            throw  new Exception(e.toString());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword){
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword); //si hace match true, si no false
    }

}
