package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Artista implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
	@NotBlank(message = "Nome do Artista deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome do Artista deve ter entre 2 e 50 letras")
    private String nome;
    
    @Size(min=1, message = "Imagem do Artista deve ser preenchida")
    @Lob
    @Type(type = "org.hibernate.type.ImageType") //Esta notação é necessário para funcionar no Postgresql
    private byte[] imagem;
    

}