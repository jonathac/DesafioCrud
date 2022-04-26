package br.com.crud.desafiocrud.repositories;

import br.com.crud.desafiocrud.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {
}
