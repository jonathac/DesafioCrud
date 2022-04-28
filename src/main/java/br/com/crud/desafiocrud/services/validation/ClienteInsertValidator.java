package br.com.crud.desafiocrud.services.validation;


import br.com.crud.desafiocrud.controllers.exception.FieldMessage;
import br.com.crud.desafiocrud.dto.NewClienteDTO;
import br.com.crud.desafiocrud.models.ClienteModel;
import br.com.crud.desafiocrud.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDTO> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(NewClienteDTO objDto, ConstraintValidatorContext context) {

        ClienteModel aux = new ClienteModel();

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(objDto.getDataNascimento());
        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            idade--;
        } else {
            if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) <= dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                idade--;
            }
        }

        List<FieldMessage> erros = new ArrayList<>();

        if (idade < 18) {
            erros.add(new FieldMessage("dataNascimento", "Insira idade maior que 18 anos"));
        }

        aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            erros.add(new FieldMessage("email", "Email já existente"));
        }

        aux = repo.findByCpf(objDto.getCpf());
        if (aux != null) {
            erros.add(new FieldMessage("cpf", "CPF já existente"));
        }

        String[] splitNome = objDto.getNome().toUpperCase(Locale.ROOT).split("\\s");

        for (int i = 0; splitNome.length > i; i++) {
            if (!splitNome[i].matches("[A-ZÀ-Ÿ]*")) {
                erros.add(new FieldMessage("nome", "ERRO NO NOME"));
            }
        }

        for (FieldMessage e : erros) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return erros.isEmpty();
    }

}