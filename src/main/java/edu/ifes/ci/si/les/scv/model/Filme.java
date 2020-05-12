package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
	@NotBlank(message = "Título do Filme deve ser preenchido")
	@Size(min = 2, max = 50, message = "Título do Filme deve ter entre 2 e 50 letras")
    private String titulo;

    @Column(length = 50)
	@NotBlank(message = "Gênero do Filme deve ser preenchido")
	@Size(min = 2, max = 50, message = "Gênero do Filme deve ter entre 2 e 50 letras")
    private String genero;

    @Column(length = 5)
	@NotBlank(message = "Duração do Filme deve ser preenchida")
	@Size(min = 2, max = 50, message = "Duração do Filme deve ter entre 5 caracteres")
    @Pattern(regexp="\\d{2}\\:\\d{2}", message = "Duração do Filme deve seguir o formato: hh:mm")
    private String duracao;

    @Size(min=1, message = "Imagem do Filme deve ser preenchida")
    @Lob
    @Type(type = "org.hibernate.type.ImageType") //Esta notação é necessária para funcionar no Postgresql
    private byte[] imagem;
    
    @NotNull(message = "O Tipo de Filme deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "tipodefilme_id")
    private TipoDeFilme tipoDeFilme;

    @NotNull(message = "O Filme deve possuir pelo menos um Diretor")
    @ManyToMany
    @JoinTable(name = "FILME_DIRETOR",
            joinColumns = @JoinColumn(name = "filme_id"),
            inverseJoinColumns = @JoinColumn(name = "diretor_id")
    )
    private Collection<Diretor> diretores = new ArrayList<>();

    @NotNull(message = "O Filme deve possuir pelo menos uma Participação")
    //orphanRemoval = true: utilizado para remover filhos (participações e filme_diretor) sem pai (filme) em caso de atualizaçao do filme (para um número de 'filhos' menor que o anterior)
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private Collection<Participacao> participacoes = new ArrayList<>();

    @Builder
    public Filme(Integer id, String titulo, String genero, String duracao, TipoDeFilme tipoDeFilme, byte[] imagem) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.tipoDeFilme = tipoDeFilme;
        this.imagem = imagem;
    }

}
