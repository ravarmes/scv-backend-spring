package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Bairro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
	@NotBlank(message = "Nome do Bairro deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome do Bairro deve ter entre 2 e 50 letras")
    private String nome;

    @NotNull(message = "Cidade do Bairro deve ser preenchida")
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

}
