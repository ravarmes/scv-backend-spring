<h1 align="center">
    <img alt="RVM" src="https://github.com/ravarmes/scv-frontend-react-redux/blob/master/public/images/logos/rvm2.jpg" />
</h1>

<h3 align="center">
  Sistema de Controle da Videolocadora - BackEnd - Spring Boot
</h3>

<p align="center">Exemplo de um Sistema para Gerenciamento de Locadora</p>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/ravarmes/scv-backend-spring?color=%2304D361">

  <a href="http://www.linkedin.com/in/rafael-vargas-mesquita">
    <img alt="Made by Rafael Vargas Mesquita" src="https://img.shields.io/badge/made%20by-Rafael%20Vargas%20Mesquita-%2304D361">
  </a>

  <img alt="License" src="https://img.shields.io/badge/license-MIT-%2304D361">

  <a href="https://github.com/ravarmes/scv-backend-spring/stargazers">
    <img alt="Stargazers" src="https://img.shields.io/github/stars/ravarmes/scv-backend-spring?style=social">
  </a>
</p>

<p align="center">
  <a href="#-sobre">Sobre o projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-instalacao">Instalação e execução</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-links">Links</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-licenca">Licença</a>
</p>

## :page_with_curl: Sobre o projeto <a name="-sobre"/></a>

> É proposto o desenvolvimento de um Sistema de Controle de Videolocadora, que vai informatizar as funções de empréstimo, devolução e reserva de fitas.

O objetivo do sistema é agilizar o processo de empréstimo e garantir maior segurança, ao mesmo tempo possibilitar um melhor controle das informações por parte da gerência. Deverão ser gerados relatórios de relacionados aos cadastros básicos, bem como aos eventos de empréstimos, devoluções e reservas. O sistema deverá calcular automaticamente o valor dos pagamentos a serem efetuados em cada empréstimo, inclusive multas e descontos devidos. A cada devolução de fitas corresponderá um pagamento, não sendo possível trabalhar com sistema de créditos. A impossibilidade de efetuar um pagamento deve deixar o cliente suspenso, ou seja, impossibilitado de tomar emprestadas novas fitas até saldar a dívida. O cliente poderá reservar fitas para pegar emprestadas. No entanto, de acordo com a política da empresa, pode ficar impossibilitado de realizar novas reservas, caso se torne recorrente a não efetivação de reservas em empréstimos.

### Manutenção de Cadastros

```
* Gerente
* Funcionário
* Cliente
* UF
* Cidade
* Bairro
* Tipo de Filme
* Filme
* Fita
* Artista
* Diretor
```

### Processos de Negócio

```
* Empréstimo de Fita
* Devolução de Fita
* Reserva de Fita
* Pagamento de Multa
```

### Relatórios

```
* Listar Empréstimos (Por Cliente, Data Início e Data Término)
* Listar Reservas (Por Cliente, Status, Data Início e Data Término)
* Listar Devoluções (Por Cliente, Filme, Data Início e Data Término)
* Listar Totais e Quantidades de Empréstimos de Clientes (Por Início e Término)
* Listar Quantidades de Empréstimos nos Bairros (Por Início e Término)
* Listar Quantidades de Reservas de Clientes (Por Status, Início e Término)
* Listar Quantidades de Devoluções de Clientes (Por Filme, Data Início e Data Término)	
```

## :computer: Instalação e execução <a name="-instalacao"/></a>

0. Instale o gerenciador de dependências [Maven](https://maven.apache.org/download.cgi);
1. Faça um clone desse repositório;
2. Entre na pasta rodando `cd scv-backend-spring`;
3. Rode `mvn spring-boot:run` para iniciar o servidor de desenvolvimento;
4. Abra `http://localhost:8080` para ver o projeto no navegador.

## :link: Links <a name="-links"/></a>

- [GitHub Pages](https://ravarmes.github.io/) - Implantação do SCV Frontend React Redux;
- [Heroku](https://scv-backend-spring.herokuapp.com/) - Implantação do SCV Backend Spring Boot;
- [Postman](https://documenter.getpostman.com/view/4048967/Szf9XTg4) - Documentação do SCV Backend Spring Boot;
- [YouTube](https://www.youtube.com/watch?v=XoejSRAPgs4&list=PL-mvLy2ws8IKOvqm2RClenHXArjQvo_pH) - Vídeos sobre o SCV Backend Spring Boot.

## :memo: Licença <a name="-licenca"/></a>

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.md) para mais detalhes.

## :email: Contato

Rafael Vargas Mesquita - [GitHub](https://github.com/ravarmes) - [LinkedIn](https://www.linkedin.com/in/rafael-vargas-mesquita) - [Lattes](http://lattes.cnpq.br/6616283627544820) - **ravarmes@hotmail.com**

---

Feito com ♥ by Rafael Vargas Mesquita :wink: