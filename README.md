# README #

### Requisitos ###

* Java 17 ou superior
* Maven

### Instalação e execução do projeto ###

1. Clonar o repositório: git clone https://Luiz_Mnck@bitbucket.org/lfmdesenv/hollywood.git
2. Construa a aplicação com o comando Maven: mvn spring-boot:run

### Acesso à API ###

O projeto  está configurado para rodar na porta 8081 no context path /hollywood/api.

Ex: http://localhost:8081/hollywood/api

### Endpoints da API ###

- GET /filme/listar: Retorna a lista de filmes do banco de dados
- POST /filme: Salva um novo filme
- PUT /filme/{id do filme}: Atualiza um filme em específico
- DELETE /filme/{id do filme}: Deleta um filme específico
- GET /vencedores/listarPorIntervalo: Obtém o produtor com maior e menor intervalo (objetivo principal do exercício)

Está disponibilizado, na raiz do projeto, uma collection do PostMan com todos os métodos e seus respectivos exemplos

### Execução dos testes ###

1. O arquivo movielist.csv já está na estrutura esperada
2. Execute o comando: mvn test

### Testes ###

Classe: VencedoresTests

A classe realiza os testes dos produtores com maior e menor intervalo.

- testarProdutorComMaiorIntervalo()
- testarProdutorComMenorIntervalo()