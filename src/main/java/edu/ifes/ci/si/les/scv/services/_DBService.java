package edu.ifes.ci.si.les.scv.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import edu.ifes.ci.si.les.scv.model.Artista;
import edu.ifes.ci.si.les.scv.model.Bairro;
import edu.ifes.ci.si.les.scv.model.Cidade;
import edu.ifes.ci.si.les.scv.model.Cliente;
import edu.ifes.ci.si.les.scv.model.Devolucao;
import edu.ifes.ci.si.les.scv.model.Diretor;
import edu.ifes.ci.si.les.scv.model.Emprestimo;
import edu.ifes.ci.si.les.scv.model.Filme;
import edu.ifes.ci.si.les.scv.model.Fita;
import edu.ifes.ci.si.les.scv.model.Funcionario;
import edu.ifes.ci.si.les.scv.model.Gerente;
import edu.ifes.ci.si.les.scv.model.ItemDeEmprestimo;
import edu.ifes.ci.si.les.scv.model.Multa;
import edu.ifes.ci.si.les.scv.model.Participacao;
import edu.ifes.ci.si.les.scv.model.Reserva;
import edu.ifes.ci.si.les.scv.model.TipoDeFilme;
import edu.ifes.ci.si.les.scv.model.UF;
import edu.ifes.ci.si.les.scv.repositories.ArtistaRepository;
import edu.ifes.ci.si.les.scv.repositories.BairroRepository;
import edu.ifes.ci.si.les.scv.repositories.CidadeRepository;
import edu.ifes.ci.si.les.scv.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scv.repositories.DevolucaoRepository;
import edu.ifes.ci.si.les.scv.repositories.DiretorRepository;
import edu.ifes.ci.si.les.scv.repositories.EmprestimoRepository;
import edu.ifes.ci.si.les.scv.repositories.FilmeRepository;
import edu.ifes.ci.si.les.scv.repositories.FitaRepository;
import edu.ifes.ci.si.les.scv.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.scv.repositories.GerenteRepository;
import edu.ifes.ci.si.les.scv.repositories.MultaRepository;
import edu.ifes.ci.si.les.scv.repositories.ParticipacaoRepository;
import edu.ifes.ci.si.les.scv.repositories.ReservaRepository;
import edu.ifes.ci.si.les.scv.repositories.TipoDeFilmeRepository;
import edu.ifes.ci.si.les.scv.repositories.UFRepository;

@Service
public class _DBService {

    @Autowired
    private ArtistaRepository artistaRepository;
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DevolucaoRepository devolucaoRepository;
    @Autowired
    private DiretorRepository diretorRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private FitaRepository fitaRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private GerenteRepository gerenteRepository;
    @Autowired
    private MultaRepository multaRepository;
    @Autowired
    private ParticipacaoRepository participacaoRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private TipoDeFilmeRepository tipoDeFilmeRepository;
    @Autowired
    private UFRepository ufRepository;

    public void instantiateTestDatabase() throws ParseException, IOException {
        
        // Instanciando os objetos de modelo
        UF uf1 = new UF(null, "ES", "Espírito Santo");
        UF uf2 = new UF(null, "MG", "Minas Gerais");

        Cidade cidade1 = new Cidade(null, "Alegre", uf1);
        Cidade cidade2 = new Cidade(null, "Cachoeiro de Itapemirim", uf1);
        Cidade cidade3 = new Cidade(null, "Belo Horizonte", uf2);
        Cidade cidade4 = new Cidade(null, "Lavras", uf2);

        Bairro bairro1 = new Bairro(null, "Vila do Sul", cidade1);
        Bairro bairro2 = new Bairro(null, "Guararema", cidade1);
        Bairro bairro3 = new Bairro(null, "Maria Ortiz", cidade2);
        Bairro bairro4 = new Bairro(null, "Centro", cidade2);
        Bairro bairro5 = new Bairro(null, "Barro Preto", cidade3);
        Bairro bairro6 = new Bairro(null, "Cidade Jardim", cidade3);
        Bairro bairro7 = new Bairro(null, "Vale do Sol", cidade4);
        Bairro bairro8 = new Bairro(null, "Nova Lavras", cidade4);

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Cliente cliente1 = new Cliente(1, "Cliente João", "111.111.111-11", "Rua Dr. Brício Mesquita", 100, bairro3, 5.0, sdf.parse("1980-01-01"));
        Cliente cliente2 = new Cliente(2, "Cliente José", "111.111.111-11", "Rua José Figueiredo", 100, bairro1, 0.0, sdf.parse("1981-01-01"));
        cliente1.getTelefones().addAll(Arrays.asList("28 1111-2222", "28 3333-4444"));
        cliente2.getTelefones().addAll(Arrays.asList("28 5555-6666", "28 7777-8888"));
                
        Funcionario funcionario1 = new Funcionario(null, "Funcionário João", "222.222.222-22", "Rua Dr. Brício Mesquita", 101, bairro3, "funcionario1", "123456");
        Funcionario funcionario2 = new Funcionario(null, "Funcionário José", "222.222.222-22", "Rua Dr. Brício Mesquita", 101, bairro3, "funcionario2", "123456");
        Gerente gerente1 = new Gerente(null, "Gerente João", "333.333.333-33", "Rua Dr. Brício Mesquita", 102, bairro3, "gerente1", "123456");
        Gerente gerente2 = new Gerente(null, "Gerente José", "333.333.333-33", "Rua Dr. Brício Mesquita", 102, bairro3, "gerente2", "123456");

        TipoDeFilme tipoDeFilme1 = new TipoDeFilme(null, "Promoção A", 1, 5.00);
        TipoDeFilme tipoDeFilme2 = new TipoDeFilme(null, "Promoção B", 2, 10.00);
        TipoDeFilme tipoDeFilme3 = new TipoDeFilme(null, "Promoção C", 3, 15.00);

        Filme filme1 = new Filme(null, "Duplo Impacto", "Ação", "02:00", tipoDeFilme1, StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/filme1.png")));
        Filme filme2 = new Filme(null, "Titanic", "Romance", "02:30", tipoDeFilme2, StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/filme2.png")));
        Filme filme3 = new Filme(null, "Avatar", "Ficção Científica", "03:00", tipoDeFilme3, StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/filme3.png")));
        Filme filme4 = new Filme(null, "Dor e glória", "Drama", "03:00", tipoDeFilme3, StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/filme4.png")));

        Artista artista1 = new Artista(null, "Jean Claude Van Damme", StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/artista1.png")));
        Artista artista2 = new Artista(null, "Geoffrey Lewis", StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/artista2.png")));
        Artista artista3 = new Artista(null, "Bolo Yeung", StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/artista3.png")));
        Artista artista4 = new Artista(null, "Leonardo DiCaprio", StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/artista4.png")));
        Artista artista5 = new Artista(null, "Kate Winslet", StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/artista5.png")));
        Artista artista6 = new Artista(null, "Sam Worthington", StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/artista6.png")));
        Artista artista7 = new Artista(null, "Zoë Saldaña", StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/artista7.png")));

        Diretor diretor1 = new Diretor(null, "Sheldon Lettich");
        Diretor diretor2 = new Diretor(null, "James Cameron");
        Diretor diretor3 = new Diretor(null, "Jon Landau");
        Diretor diretor4 = new Diretor(null, "Quentin Tarantino");

        Participacao participacao1 = new Participacao(null, "Alex", filme1, artista1);
        Participacao participacao2 = new Participacao(null, "Chad", filme1, artista1);
        Participacao participacao3 = new Participacao(null, "Frank", filme1, artista2);
        Participacao participacao4 = new Participacao(null, "Moon", filme1, artista3);
        Participacao participacao5 = new Participacao(null, "Jack", filme2, artista4);
        Participacao participacao6 = new Participacao(null, "Rose", filme2, artista5);
        Participacao participacao7 = new Participacao(null, "Jake Sully", filme3, artista6);
        Participacao participacao8 = new Participacao(null, "Neytiri", filme3, artista7);

        Fita fita1 = new Fita(null, false, false, filme1);  // Fita indisponível, pois apesar de emprestada e devolvida, possui reserva em aberto
        Fita fita2 = new Fita(null, false, true, filme2);  // Fita disponível, pois será emprestada, mas devolvida
        Fita fita3 = new Fita(null, false, false, filme3); // Fita indisponível, pois será emprestada, ainda sem devolução 
        Fita fita4 = new Fita(null, false, true, filme3);  // Fita disponível, não associada a empréstimos
        Fita fita5 = new Fita(null, false, true, filme4);  // Fita disponível, não associada a empréstimos

        Emprestimo emprestimo1 = new Emprestimo(null, sdf.parse("2020-04-10"), 15.00, cliente1);
        Emprestimo emprestimo2 = new Emprestimo(null, sdf.parse("2020-04-13"), 10.00, cliente2);

        ItemDeEmprestimo itemDeEmprestimo1 = new ItemDeEmprestimo(emprestimo1, fita1, 5.00, sdf.parse("2020-04-11"));
        ItemDeEmprestimo itemDeEmprestimo2 = new ItemDeEmprestimo(emprestimo1, fita2, 10.00, sdf.parse("2020-04-12"));
        ItemDeEmprestimo itemDeEmprestimo3 = new ItemDeEmprestimo(emprestimo2, fita3, 10.00, sdf.parse("2020-04-16"));

        Devolucao devolucao1 = new Devolucao(itemDeEmprestimo1, sdf.parse("2020-04-12")); // Devolução com atraso de um dia
        Devolucao devolucao2 = new Devolucao(itemDeEmprestimo2, sdf.parse("2020-04-12"));

        Multa multa1 = new Multa(itemDeEmprestimo1, 5.00, false); // Multa gerada para a devolucao1, com atraso de 1 dia, ainda não paga

        Reserva reserva1 = new Reserva(null, sdf.parse("2020-04-13"), 0, cliente1, fita1); // Reserva em aberto (status 0)

        filme1.setDiretores(Arrays.asList(diretor1));
        filme2.setDiretores(Arrays.asList(diretor2));
        filme3.setDiretores(Arrays.asList(diretor2, diretor3));

        emprestimo1.setItens(Arrays.asList(itemDeEmprestimo1, itemDeEmprestimo2));
        emprestimo2.setItens(Arrays.asList(itemDeEmprestimo3));

        // Trabalhando com o banco de dados utilizando os Repositories
        artistaRepository.saveAll(Arrays.asList(artista1, artista2, artista3, artista4, artista5, artista6, artista7));
        diretorRepository.saveAll(Arrays.asList(diretor1, diretor2, diretor3, diretor4));
        ufRepository.saveAll(Arrays.asList(uf1, uf2));
        cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
        bairroRepository.saveAll(Arrays.asList(bairro1, bairro2, bairro3, bairro4, bairro5, bairro6, bairro7, bairro8));
        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
        funcionarioRepository.saveAll(Arrays.asList(funcionario1, funcionario2));
        gerenteRepository.saveAll(Arrays.asList(gerente1, gerente2));
        tipoDeFilmeRepository.saveAll(Arrays.asList(tipoDeFilme1, tipoDeFilme2, tipoDeFilme3));
        filmeRepository.saveAll(Arrays.asList(filme1, filme2, filme3, filme4));
        fitaRepository.saveAll(Arrays.asList(fita1, fita2, fita3, fita4, fita5));

        
        emprestimoRepository.saveAll(Arrays.asList(emprestimo1, emprestimo2)); //Inserindo empréstimo 1 e seus itens (cascade)
        devolucaoRepository.saveAll(Arrays.asList(devolucao1, devolucao2));
        multaRepository.saveAll(Arrays.asList(multa1));
        reservaRepository.saveAll(Arrays.asList(reserva1));
        participacaoRepository.saveAll(Arrays.asList(participacao1, participacao2, participacao3, participacao4, participacao5, participacao6, participacao7, participacao8));
		

    }
}
