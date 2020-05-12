package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Data do Empréstimo deve ser preenchida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @Min(value = 1, message = "Valor do Empréstimo deve ser maior que zero")
    @NotNull(message = "Valor do Empréstimo deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor do Empréstimo deve ser preenchido com dígitos")
    private Double valor;

    @NotNull(message = "O Cliente do Empréstimo deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull(message = "O Empréstimo deve possuir pelo menos um Item de Empréstimo")
    //orphanRemoval = true: utilizado para remover filhos (itens) sem pai (empréstimo) em caso de atualizaçao do empréstimo (para um número de itens menor que o anterior)
    @OneToMany(mappedBy = "id.emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ItemDeEmprestimo> itens = new ArrayList<>();

    @Builder
    public Emprestimo(Integer id, Date data, Double valor, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.cliente = cliente;
    }
        
}
