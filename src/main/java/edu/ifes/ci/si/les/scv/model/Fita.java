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
public class Fita implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "O atributo 'danificada' da Fita deve ser preenchido")
	private Boolean danificada;

	@NotNull(message = "O atributo 'disponivel' da Fita deve ser preenchido")
	@NotNull(message = "O Filme da Fita deve ser preenchido")
	private Boolean disponivel;

	@NotNull(message = "O Filme da Fita deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "filme_id")
	private Filme filme;

}
