# worst-golden-raspberry-awards-movie-api
Projeto de Teste Raspberry Awards API desenvolvido com Spring Boot 4

### Direcionamentos para execução do projeto no STS:

1 - Clonar o projeto para sua Workspace;

2 - Utilizar o Maven para importar um projeto existente apontando para a pasta do projeto em sua Workspace;

3 - Executar o projeto importado através do Spring Boot App. Ao inicializar o projeto os dados do CSV serão importados
para o banco H2 em memória;
4 - Executar uma chamada através da URL "http://localhost:8080/movies/min-max-interval-between-two-awards";

### Direcionamento para execução dos testes:

1 - Com o servidor desligado acessar a classe "WorstGoldenRaspberryAwardsMovieApiApplicationTests" presente dentro do diretório
"src/test/java/br/com/worstmovie"

2 - Executar o JUnit Test

### URL Disponíveis

1 - Banco de Dados H2: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:~/moviedb, Usuário: sa, Senha: )

2 - URL do Endpoint: http://localhost:8080/movies/min-max-interval-between-two-awards
