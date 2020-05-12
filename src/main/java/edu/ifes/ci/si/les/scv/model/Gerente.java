package edu.ifes.ci.si.les.scv.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Gerente extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(length = 20)
    @NotBlank(message = "Login do Gerente deve ser preenchido")
    @Size(min = 2, max = 50, message = "Login do Funcionário deve ter entre 2 e 20 caracteres")
    private String login;

    @Column(length = 20)
    @NotBlank(message = "Senha do Gerente deve ser preenchida")
    @Size(min = 6, max = 10, message = "Senha do Funcionário deve ter entre 6 e 20 caracteres")
    private String senha;

    @Builder
    public Gerente(Integer id, String nome, String cpf, String rua, Integer numero, Bairro bairro, String login, String senha) {
        super(id, nome, cpf, rua, numero, bairro);
        this.login = login;
        this.senha = senha;
    }

}
