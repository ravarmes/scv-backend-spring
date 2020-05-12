package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

//Classe criada para representar a chave primária composta do objeto Multa
@Embeddable
@Data
@EqualsAndHashCode(of = {"itemDeEmprestimo"})
public class MultaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "FITA_ID", referencedColumnName = "FITA_ID"),
        @JoinColumn(name = "EMPRESTIMO_ID", referencedColumnName = "EMPRESTIMO_ID")
    })
    private ItemDeEmprestimo itemDeEmprestimo = new ItemDeEmprestimo();

}
