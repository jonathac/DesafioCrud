package br.com.crud.desafiocrud.dto;

import br.com.crud.desafiocrud.services.validation.ClienteInsert;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;


@ClienteInsert
public class NewClienteDTO {

    @NotBlank(message = "Preenchimento obrigatorio")
    @Length(max = 120, message = "Quantidade de caracteres deve ser no maximo 120")
    private String nome;

    @NotBlank(message = "Preenchimento obrigatorio")
    @CPF
    private String cpf;

    @Pattern(regexp = "^([\\w\\-]+\\.)*[\\w\\- ]+@([\\w\\- ]+\\.)+([\\w\\-]{2,3})$", message = "E-mail inválido!")
    @NotBlank(message = "Preenchimento obrigatorio")
    private String email;

   // @JsonFormat(pattern = "dd/MM/yyyy")
    //private Date dataNascimento;

    private String dataNascimento;

    public NewClienteDTO() {
    }

    public NewClienteDTO(String nome, String cpf, String email, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}