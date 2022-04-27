package br.com.crud.desafiocrud.dto;

import br.com.crud.desafiocrud.services.validation.ClienteUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@ClienteUpdate
public class UpdateClienteDto {

    @NotBlank(message = "Preenchimento obrigatorio")
    @Length(max = 120,message = "Quantidade de caracteres deve ser no maximo 120")
    private String nome;

    @NotBlank(message = "Preenchimento obrigatorio")
    @Email
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    public UpdateClienteDto(){}

    public UpdateClienteDto(String nome, String email, Date dataNascimento){
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}