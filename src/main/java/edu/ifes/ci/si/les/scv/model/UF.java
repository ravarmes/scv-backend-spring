package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class UF implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 2)
	@NotBlank(message = "Sigla da UF deve ser preenchida")
	@Size(min = 2, max = 2, message = "Sigla da UF deve ter 2 letras")
    private String sigla;

    @Column(length = 50)
	@NotBlank(message = "Nome da UF deve ser preenchida")
	@Size(min = 2, max = 50, message = "Nome da UF deve ter entre 2 e 50 letras")
    private String nome;

}
