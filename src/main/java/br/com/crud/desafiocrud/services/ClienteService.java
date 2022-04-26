package br.com.crud.desafiocrud.services;

import br.com.crud.desafiocrud.models.ClienteModel;
import br.com.crud.desafiocrud.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    //CREATE
    public ClienteModel create(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    //READ
    public Optional<ClienteModel> read(Integer idCliente) {
        Optional<ClienteModel> clienteModel = clienteRepository.findById(idCliente);

        return clienteModel;
    }

    //READ ALL

    public List<ClienteModel> readAll() {
        return clienteRepository.findAll();
    }

    //UPDATE

    public ClienteModel update(ClienteModel clienteModel) {
        ClienteModel newClienteModel = read(clienteModel.getIdCliente()).get();
        updateCliente(clienteModel,newClienteModel);
        return clienteRepository.save(newClienteModel);
    }

    public void updateCliente (ClienteModel clienteModel, ClienteModel newClienteModel){
    newClienteModel.setEmail(clienteModel.getEmail());
    newClienteModel.setNome(clienteModel.getNome());
    }

    //DELETE

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }


}
//C-reate
//R-ead
//U-pdate
//D-elete
