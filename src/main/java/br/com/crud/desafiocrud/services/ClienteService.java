package br.com.crud.desafiocrud.services;

import br.com.crud.desafiocrud.dto.NewClienteDTO;
import br.com.crud.desafiocrud.dto.UpdateClienteDto;
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
    newClienteModel.setDataNascimento(clienteModel.getDataNascimento());
    }

    //DELETE

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }

    //CLIENTE FROM DTO
    public ClienteModel fromNewDto (NewClienteDTO clienteDto){
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome(clienteDto.getNome());
        clienteModel.setEmail(clienteDto.getEmail());
        clienteModel.setCpf(clienteDto.getCpf());
        clienteModel.setDataNascimento(clienteDto.getDataNascimento());
        return clienteModel;
    }

    //UPDATE CLIENTEDTO
    public ClienteModel fromUpdateDto (UpdateClienteDto updateClienteDto){
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome(updateClienteDto.getNome());
        clienteModel.setEmail(updateClienteDto.getEmail());
        clienteModel.setDataNascimento(updateClienteDto.getDataNascimento());
        return clienteModel;
    }
}
//C-reate
//R-ead
//U-pdate
//D-elete
