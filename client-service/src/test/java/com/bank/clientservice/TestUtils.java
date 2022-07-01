package com.bank.clientservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;

public class TestUtils {

    private static ObjectMapper objectMapper;
    private static ModelMapper modelMapper;

    public static ObjectMapper objectMapper(){
        if(objectMapper == null){
            objectMapper = new ObjectMapper().findAndRegisterModules();
        }
        return objectMapper;
    }

    public static ModelMapper modelParser(){
        if(modelMapper == null){
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }

    public static byte[] serializeAsBytes(Object object) throws JsonProcessingException {
        return objectMapper().writeValueAsBytes(object);
    }
}
