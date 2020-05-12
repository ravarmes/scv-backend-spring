package edu.ifes.ci.si.les.scv.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import edu.ifes.ci.si.les.scv.model.enums.StatusReserva;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @Digits(integer=1, fraction=0, message = "Número da Casa da Pessoa deve ser preenchido com um valor inteiro")
    private Integer status;

    @NotNull(message = "O Cliente da Reserva deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull(message = "A Fita da Reserva deve ser preenchida")
    @ManyToOne
    @JoinColumn(name = "fita_id")
    private Fita fita;

    @Builder
    public Reserva(Integer id, Date data, StatusReserva status, Cliente cliente, Fita fita) {
        this.id = id;
        this.data = data;
		this.status = (status==null) ? null : status.getCod();
        this.cliente = cliente;
        this.fita = fita;
    }
    
    public StatusReserva getStatus() {
		return StatusReserva.toEnum(status);
	}

	public void setStatus(StatusReserva status) {
		this.status = status.getCod();
	}

}
