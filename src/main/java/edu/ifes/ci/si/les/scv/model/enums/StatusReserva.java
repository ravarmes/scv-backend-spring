package edu.ifes.ci.si.les.scv.model.enums;

public enum StatusReserva {

	ABERTA(0, "Reserva em aberto"),
	FECHADAEMPRESTIMO(1, "Reserva fechada por ocasião de empréstimo"),
	FECHADACANCELADA(2, "Reserva fechada por cancelamento");
	
	private int cod;
	private String descricao;
	
	private StatusReserva(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static StatusReserva toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatusReserva x : StatusReserva.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
