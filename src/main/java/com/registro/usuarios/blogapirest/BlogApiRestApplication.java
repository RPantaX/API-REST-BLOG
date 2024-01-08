package com.registro.usuarios.blogapirest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApiRestApplication {
    @Bean //creamos este bean para que lo registre en la fábrica de spring para luego poder inyectarlo
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApiRestApplication.class, args);
    }

}
