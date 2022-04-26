package br.com.crud.desafiocrud.controllers;

import br.com.crud.desafiocrud.models.ClienteModel;
import br.com.crud.desafiocrud.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    @PostMapping()
    public ResponseEntity<ClienteModel> create(@RequestBody ClienteModel clienteModel) {
        clienteService.create(clienteModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteModel.getIdCliente()).toUri();
        return ResponseEntity.created(uri).build();

        // status(HttpStatus.CREATED).body(endpoint);
    }

    //READ ID
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Integer id) {
        Optional<ClienteModel> clienteModel = clienteService.read(id);
        if (clienteModel.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(clienteModel);
    }

    //READ ALL
    @GetMapping()
    public ResponseEntity<List<ClienteModel>> readAll() {
        List<ClienteModel> list = new ArrayList();
        list = clienteService.readAll();
        return ResponseEntity.ok().body(list);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClienteModel cliente) {
        //Optional<ClienteModel> clienteModel = clienteService.read(id);
        if (clienteService.read(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cliente.setIdCliente(id);
        clienteService.update(cliente);
        return ResponseEntity.ok().build();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (clienteService.read(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
