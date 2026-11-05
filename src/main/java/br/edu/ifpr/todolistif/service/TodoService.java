package br.edu.ifpr.todolistif.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.todolistif.dto.TodoConclusaoDTO;
import br.edu.ifpr.todolistif.dto.TodoRequestDTO;
import br.edu.ifpr.todolistif.dto.TodoResponseDTO;
import br.edu.ifpr.todolistif.model.Todo;
import br.edu.ifpr.todolistif.repository.TodoRepository;

@Service 
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<TodoResponseDTO> listar(){
    
        List<Todo> tarefas = repository.findAll();
        return converter(tarefas);
        
    }

    // id é gerado pelo banco e createdAt pelo construtor de Todo;
    // finishedAt começa null
    public TodoResponseDTO inserir(TodoRequestDTO dto) {
        Todo tarefa = new Todo();
        tarefa.setTitle(dto.title());
        tarefa.setDeadLine(dto.deadLine());
        Todo salva = repository.save(tarefa);
        return new TodoResponseDTO(salva);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // atualiza somente finishedAt, os demais dados são preservados
    public TodoResponseDTO concluir(Long id, TodoConclusaoDTO dto) {
        Todo tarefa = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
        tarefa.setFinishedAt(dto.finishedAt());
        Todo salva = repository.save(tarefa);
        return new TodoResponseDTO(salva);
    }

    public List<TodoResponseDTO> listarConcluidas() {
        return converter(repository.findByFinishedAtIsNotNull());
    }

    public List<TodoResponseDTO> listarPendentes() {
        return converter(repository.findByFinishedAtIsNull());
    }

    private List<TodoResponseDTO> converter(List<Todo> tarefas) {
        List<TodoResponseDTO> resposta = new ArrayList<>();

        for (Todo tarefa : tarefas) {
            resposta.add(new TodoResponseDTO(tarefa));
        }
        return resposta;
    }

}
