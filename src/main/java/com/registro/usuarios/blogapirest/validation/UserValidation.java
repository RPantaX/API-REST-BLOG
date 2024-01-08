package com.registro.usuarios.blogapirest.validation;

import com.registro.usuarios.blogapirest.dto.ResponseDTO;
import com.registro.usuarios.blogapirest.entities.Usuario;

public class UserValidation {
    public ResponseDTO validate(Usuario user){
        ResponseDTO response = new ResponseDTO();

        response.setNumOfErrors(0);
        //FIRST NAME
        if(user.getNombre()==null ||
                user.getNombre().length()<3 ||
                user.getNombre().length()>15
        ){
            response.setNumOfErrors(response.getNumOfErrors()+1);
            response.setMessage("The name cannot be null, it also has to be between 3 and 15 characters long");
        }
        //USERNAME
        if(user.getUsername()==null ||
                user.getUsername().length()<3 ||
                user.getUsername().length()>30
        ){
            response.setNumOfErrors(response.getNumOfErrors()+1);
            response.setMessage("The username cannot be null, it also has to be between 3 and 30 characters long");
        }
        //EMAIL
        if(user.getEmail()==null ||
                !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors()+1);
            response.setMessage("The email is invalid");
        }
        //PASSWORD
        if(user.getPassword()==null ||
                !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors()+1);
            response.setMessage("The password must be between 8 and 16 characters long, minimum one number, one lowercase and one uppercase letter");
        }
        return response;
    }
}
