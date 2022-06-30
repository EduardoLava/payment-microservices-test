package com.bank.clientservice.modules.resource;

import com.bank.clientservice.modules.dto.ClientRequestDTO;
import com.bank.clientservice.modules.dto.ClientResponseDTO;
import com.bank.clientservice.modules.entity.Client;
import com.bank.clientservice.modules.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "clients")
public class ClientResource {

    public ClientResource(ClientService clientService, ModelMapper modelMapper){
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    private ClientService clientService;

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> insert(@RequestBody @Valid ClientRequestDTO clientRequestDTO){
        var client = modelMapper.map(clientRequestDTO, Client.class);
        client = clientService.save(client);
        var response = modelMapper.map(client, ClientResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable("id") Integer id){
        var client = clientService.findById(id);
        if(client == null){
            return ResponseEntity.notFound().build();
        }
        var response = modelMapper.map(client, ClientResponseDTO.class);
        return ResponseEntity.ok(response);
    }
}
