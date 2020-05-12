package edu.ifes.ci.si.les.scv.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scv.model.Cliente;
import edu.ifes.ci.si.les.scv.model.Emprestimo;
import edu.ifes.ci.si.les.scv.model.Fita;
import edu.ifes.ci.si.les.scv.model.ItemDeEmprestimo;
import edu.ifes.ci.si.les.scv.model.Reserva;
import edu.ifes.ci.si.les.scv.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scv.repositories.EmprestimoRepository;
import edu.ifes.ci.si.les.scv.repositories.FitaRepository;
import edu.ifes.ci.si.les.scv.repositories.ReservaRepository;
import edu.ifes.ci.si.les.scv.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scv.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scv.services.exceptions.ObjectNotFoundException;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private FitaRepository fitaRepository;
	@Autowired
	private ReservaRepository reservaRepository;

	public Emprestimo findById(Integer id) {
		try {
        	Emprestimo obj = emprestimoRepository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Emprestimo.class.getName());
        }
	}

	public Collection<Emprestimo> findAll() {
		return emprestimoRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) // Esta notação tem objetivo de controlar a propagação da transação (garantindo que sejam realizadas todas as modificações no BD, ou nada)
	public Emprestimo insert(Emprestimo obj) {
		try {
			if (verificarRegrasDeNegocio(obj)) {
				obj.setId(null);
				for (ItemDeEmprestimo item : obj.getItens()) {
					item.setEmprestimo(obj);
					item.getFita().setDisponivel(false); // Alterando a disponibilidade de fita
					fitaRepository.save(item.getFita());
				}
				return emprestimoRepository.save(obj);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Empréstimo não foi(foram) preenchido(s): Cliente ou Item de Empréstimo");
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) // Esta notação tem objetivo de controlar a propagação da transação (garantindo que sejam realizadas todas as modificações no BD, ou nada)
	public Emprestimo update(Emprestimo obj) {
		try {
			findById(obj.getId());
			fitaRepository.updateDisponivelByEmprestimo(obj.getId()); // Alterando a disponibilidade das fitas não mais associadas a este empréstimo
			for (ItemDeEmprestimo item : obj.getItens()) {
				item.setEmprestimo(obj);
				item.getFita().setDisponivel(false); // Alterando a disponibilidade das novas fitas emprestadas
				fitaRepository.save(item.getFita());
			}
			return emprestimoRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Empréstimo não foi(foram) preenchido(s): Cliente ou Item de Empréstimo");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW) //Todas as operações de persistência no BD serão realizadas em uma única transação (se tudo der certo commit, senão rollback em tudo).
	public void delete(Integer id) {
		findById(id);
		try {
			fitaRepository.updateDisponivelByEmprestimo(id); // Alterando a disponibilidade das fitas não mais associadas a este empréstimo
			emprestimoRepository.deleteById(id);
			emprestimoRepository.flush();  // Forçando a sincronização da(s) alteração(ões) e remoção neste momento (assim, diante de qualquer problema, o catch conseguirá capturar a exceção nesta camada de serviço) 
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Empréstimo que possui multas ou devoluções!");
		} 
	}

	// Implementando as regras de negócio relacionadas ao processo de negócio Empréstimo
	// Regra de Negócio 1: Cliente não pode ter multas não pagas
	// Regra de Negócio 2: Não podem ser emprestadas fitas reservadas para outros clientes
	// Regra de Negócio 3: Não podem ser emprestadas fitas com status disponível false
	public boolean verificarRegrasDeNegocio(Emprestimo obj) {
		
		// Regra de Negócio 1: Cliente não pode ter multas não pagas
		Collection<Cliente> devedores = clienteRepository.findDevedores();
		// boolean clienteDevedor = devedores.contains(obj.getCliente());
		boolean clienteDevedor = false;
		for (Cliente devedor : devedores) {
			if (devedor.getId() == obj.getCliente().getId()) {
				clienteDevedor = true;
			}
		}
		if (clienteDevedor) {
			throw new BusinessRuleException("Este cliente deve multas anteriores!");
		}

		// Regra de Negócio 2: Não podem ser emprestadas fitas reservadas para outros clientes
		boolean fitasReservadas = false;
		for (ItemDeEmprestimo item : obj.getItens()) {
			// Verificando se existem reservas em aberto para a fita
			Reserva reserva = reservaRepository.findByFitaAndStatus(item.getId().getFita().getId(), 0);
			if (reserva != null) {
				fitasReservadas = true;
			}
		}
		if (fitasReservadas) {
			throw new BusinessRuleException("Existem fitas com reservadas em aberto!");
		}

		// Regra de Negócio 3: Não podem ser emprestadas fitas com status disponível false
		boolean fitasDisponiveis = true;
		for (ItemDeEmprestimo item : obj.getItens()) {
			// Verificando se existem fitas com status disponível false
			Fita fita = fitaRepository.findByIdAndDisponivel(item.getId().getFita().getId(), false);
			if (fita != null) {
				fitasDisponiveis = false;
			}
		}
		if (!fitasDisponiveis) {
			throw new BusinessRuleException("Existem fitas não disponíveis para empréstimo!");
		}

		if (!clienteDevedor && !fitasReservadas && fitasDisponiveis) {
			return true;
		} else {
			return false;
		}
	}

	public Collection<Emprestimo> findByCliente(Cliente cliente) {
		return emprestimoRepository.findByCliente(cliente);
	}

	public Collection<Emprestimo> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino) {
		return emprestimoRepository.findByClienteAndPeriodo(idCliente, inicio, termino);
	}

	public Collection<?> findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(String inicio, String termino) {
		return emprestimoRepository.findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(inicio, termino);
	}

	public Collection<?> findQuantidadesEmprestimosOfBairrosByPeriodo(String inicio, String termino) {
		return emprestimoRepository.findQuantidadesEmprestimosOfBairrosByPeriodo(inicio, termino);
	}

	public Collection<?> findQuantidadesEmprestimosOfFilmesByPeriodo(String inicio, String termino) {
		return emprestimoRepository.findQuantidadesEmprestimosOfFilmesByPeriodo(inicio, termino);
	}

	public Collection<?> findTotaisAnoMes() {
		return emprestimoRepository.findTotaisAnoMes();
	}

}
