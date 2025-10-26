<h1 align="center">
  TODO List
</h1>

API para gerenciar tarefas (CRUD) que faz parte [desse desafio](https://github.com/simplify-liferay/desafio-junior-backend-simplify) para pessoas desenvolvedoras backend júnior.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Mysql](https://dev.mysql.com/downloads/)

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/todolist-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [httpie](https://httpie.io):

- Criar Tarefa
```
$ http POST :8080/todo/cadastrar name="Todo 1" descricao="Desc Todo 1" prioridade=1

[
  {
    "id": 1,
    "nome": "Todo 1",
    "descricao": "Desc Todo 1",
    "realizado": false,
    "prioridade": 1
  }
]
```

- Listar Tarefas
```
$ http GET :8080/todo

[
  {
   "id": 1,
    "nome": "Todo 1",
    "descricao": "Desc Todo 1",
    "realizado": false,
    "prioridade": 1
  }
]
```

- Atualizar Tarefa
```
$ http PUT :8080/todo/atualizar/1 name="Todo 1 Up" descricao="Desc Todo 1 Up" prioridade=2

[
  {
    "id": 1,
    "nome": "Todo 1 Up",
    "descricao": "Desc Todo 1 Up",
    "realizado": true,
    "prioridade": 2
  }
]
```

- Remover Tarefa
```
http DELETE :8080/todo/deletar/1

[ ]
```