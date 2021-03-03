# Vitrine Back-End
Descrição: É Utilizado o spring boot e padrãos de projeto para estruturar o projeto com "boas práticas", postgres é usado como banco de dados.

## Project setup

### Application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/vitrine
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
server.port=8090

Levando em consideração que você tenha criado um banco com o nome "vitrine" e configurando os demais campos, implica na comunicação entre back-end e bancos de dados.
```

### Build & Run
```
Utilizando o Eclipse:
src/main/java > com.vitrine.livraria, temos o arquivo LivrariaApplication, onde será executado através de um "Java Application.
botão direito > Run As > Java Application.
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
