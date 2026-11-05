package br.edu.ifpr.todolistif.dto;

import java.time.LocalDateTime;

// DTO de entrada do POST /api/todos: só o que o cliente pode informar
public record TodoRequestDTO(String title, LocalDateTime deadLine) {
}
