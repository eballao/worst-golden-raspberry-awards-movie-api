# worst-golden-raspberry-awards-movie-api
Projeto de Teste Raspberry Awards API desenvolvido com Spring Boot 4

### Direcionamentos para execução do projeto no STS:

1 - Clonar o projeto para sua Workspace;

2 - Utilizar o Maven para importar um projeto existente apontando para a pasta do projeto em sua Workspace;

3 - Executar o projeto importado através do Spring Boot App. Ao inicializar o projeto os dados do CSV serão importados
para o banco H2 em memória;

4 - Executar uma chamada através da URL "http://localhost:8080/autorizador/cadastro/procedimento/9999/idade/20/sexo/M/autoriza/SIM", por exemplo para inserir uma nova autorização;

5 - Executar uma chamada através da URL
"http://localhost:8080/autorizador/procedimento/1234/idade/10/sexo/M", por exemplo para verificar se o procedimento esta disponível

### Direcionamento para execução dos testes:

1 - Com o servidor desligado acessar a classe "ProcedimentoRepositorioTest" presente dentro do diretório
"src/test/java/com/com/qualirede/api/repositorio"

2 - Executar o JUnit Test

### URL Disponíveis

1 - Banco de Dados H2: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:~/qualirededb, Usuário: sa, Senha: )

2 - URL para registro da regra de autorização: http://localhost:8080/autorizador/cadastro/procedimento/{}/idade/{}/sexo/{}/autoriza/{}

2 - URL para solicitação de autorização: 
http://localhost:8080/autorizador/procedimento/{}/idade/{}/sexo/{}

Obs: as "{}" devem ser subistituidas pelos valores respectivos aos campos de filtragem para autorização.
