package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

//Classe criada para representar a chave primária composta do objeto ItemDeEmprestimo
@Embeddable
@Data
@EqualsAndHashCode(of = {"emprestimo", "fita"})
public class ItemDeEmprestimoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;

    @ManyToOne
    @JoinColumn(name = "fita_id")
    private Fita fita;
    

}
