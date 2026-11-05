# API de tarefas (todolistif)

Atividade bimestral de POO, IFPR Cascavel, Técnico Integrado em Informática.

**Integrante:** Laura Radünz Pedro

## Como uma requisição funciona

A requisição chega no `TodoApiController`, que só recebe o pedido e devolve o status HTTP. Ele passa o trabalho pro `TodoService`, que cria a tarefa, marca como concluída, converte pra DTO e decide o que fazer. Por fim, o `TodoService` chama o `TodoRepository`, que é quem fala com o banco.

- **Controller:** recebe o pedido e define a resposta (201, 204, 200).
- **Service:** faz as operações e as regras.
- **Repository:** salva, busca e exclui no banco.

## DTOs

O DTO de resposta não tinha o `finishedAt`, então o cliente não conseguia ver a data de conclusão. Além disso, cada operação recebe dados diferentes, então separamos em três DTOs:

- `TodoRequestDTO`: usado no POST, recebe só `title` e `deadLine`. O `id` e o `createdAt` o sistema gera sozinho.
- `TodoConclusaoDTO`: usado no PATCH, recebe só o `finishedAt`. Assim só esse campo muda e o resto da tarefa continua igual.
- `TodoResponseDTO`: usado nas respostas. Agora ele tem o `finishedAt`, então dá pra conferir a data registrada.

## Exemplos de requisições

**POST** `/api/todos` (retorna 201)

```json
{
  "title": "Estudar Spring Boot",
  "deadLine": "2026-10-30T18:00:00"
}
```

Resposta:

```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "createdAt": "2026-11-05T08:05:15.626338",
  "deadLine": "2026-10-30T18:00:00",
  "finishedAt": null
}
```

**PATCH** `/api/todos/1/conclusao` (retorna 200)

```json
{
  "finishedAt": "2026-10-22T10:30:00"
}
```

Resposta:

```json
{
  "id": 1,
  "title": "Estudar Spring Boot",
  "createdAt": "2026-11-05T08:05:15.626338",
  "deadLine": "2026-10-30T18:00:00",
  "finishedAt": "2026-10-22T10:30:00"
}
```

**DELETE** `/api/todos/1` (retorna 204, sem corpo)

**GET** `/api/todos/concluidas` (retorna 200): tarefas com `finishedAt` preenchido. Se não tiver nenhuma, volta `[]`.

**GET** `/api/todos/pendentes` (retorna 200): tarefas com `finishedAt` null. Se não tiver nenhuma, volta `[]`.

**GET** `/api/todos` (retorna 200): lista todas as tarefas.