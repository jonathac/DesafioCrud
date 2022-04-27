package br.com.crud.desafiocrud.repositories;

import br.com.crud.desafiocrud.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

    @Transactional(readOnly=true)
    ClienteModel findByEmail(String email);

    @Transactional(readOnly=true)
    ClienteModel findByCpf(String cpf);
}
