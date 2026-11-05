package br.edu.ifpr.todolistif.dto;

import java.time.LocalDateTime;

// DTO de entrada do PATCH /api/todos/{id}/conclusao: só a data de conclusão
public record TodoConclusaoDTO(LocalDateTime finishedAt) {
}
