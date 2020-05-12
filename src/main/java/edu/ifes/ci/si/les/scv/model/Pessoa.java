package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @NotBlank(message = "Nome da Pessoa deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome da Pessoa deve ter entre 2 e 50 letras")
    private String nome;

    @Column(length = 50)
    @NotBlank(message = "CPF da Pessoa deve ser preenchido")
    @Size(min = 2, max = 50, message = "CPF da Pessoa deve ter entre 2 e 50 letras")
    @Pattern(regexp="\\d{3}.\\d{3}.\\d{3}-\\d{2}", message = "CPF da Pessoa deve seguir o padrão NNN.NNN.NNN-NN")
    private String cpf;

    @Column(length = 50)
    @NotBlank(message = "Rua da Pessoa deve ser preenchido")
    @Size(min = 2, max = 50, message = "Rua da Pessoa deve ter entre 2 e 50 letras")
    private String rua;

    @Digits(integer=4, fraction=0, message = "Número da Casa da Pessoa deve ser preenchido com um valor inteiro")
    private Integer numero;

    @NotNull(message = "O Bairro da Pessoa deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;

}
