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
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50) 
    @NotBlank(message = "Nome da Cidade deve ser preenchido") 
    @Size(min = 3, max = 50, message = "Nome da Cidade deve ter entre 3 e 50 letras")  
    private String nome;

    @NotNull(message = "A UF da Cidade deve ser preenchida") 
    @ManyToOne
    @JoinColumn(name = "uf_id")
    private UF uf;

}
