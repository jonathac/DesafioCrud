package br.com.crud.desafiocrud.controllers;

import br.com.crud.desafiocrud.dto.NewClienteDTO;
import br.com.crud.desafiocrud.dto.UpdateClienteDto;
import br.com.crud.desafiocrud.models.ClienteModel;
import br.com.crud.desafiocrud.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //CREATE
    @ApiOperation("Cadastrar Cliente")
    @PostMapping()
    public ResponseEntity<ClienteModel> create(@Valid @RequestBody NewClienteDTO clienteDto) {
        ClienteModel clienteModel = clienteService.fromNewDto(clienteDto);
        clienteService.create(clienteModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteModel.getIdCliente()).toUri();
        return ResponseEntity.created(uri).build();

        // status(HttpStatus.CREATED).body(endpoint);
    }

    //READ ID
    @ApiOperation("Buscar Cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Integer id) {
        Optional<ClienteModel> clienteModel = clienteService.read(id);
        if (clienteModel.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(clienteModel);
    }

    //READ ALL
    @ApiOperation("Buscar todos os clientes")
    @GetMapping()
    public ResponseEntity<List<ClienteModel>> readAll() {
        List<ClienteModel> list = new ArrayList();
        list = clienteService.readAll();
        return ResponseEntity.ok().body(list);
    }

    //UPDATE
    @ApiOperation("Atualizar cliente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@Valid @RequestBody UpdateClienteDto updateClienteDto) {
        ClienteModel clienteModel = clienteService.fromUpdateDto(updateClienteDto);
        if (clienteService.read(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clienteModel.setIdCliente(id);
        clienteService.update(clienteModel);
        return ResponseEntity.ok().build();
    }

    //DELETE
    @ApiOperation("Apagar Cliente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (clienteService.read(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
