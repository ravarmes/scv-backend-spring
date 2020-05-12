package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Participacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @NotBlank(message = "Personagem da Participação deve ser preenchido")
    @Size(min = 2, max = 50, message = "Personagem da Participação deve ter entre 2 e 50 letras")
    private String personagem;

    @JsonIgnore //Evitando referência cíclica
    @NotNull(message = "O Filme da Participação deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    @NotNull(message = "O Artista da Participação deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

}
