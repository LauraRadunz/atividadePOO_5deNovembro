// package br.edu.ifpr.todolistif.excpetion;

// import java.util.Map;
// import java.util.NoSuchElementException;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice 
// public class ErroHandler {
//     @ExceptionHandler(NoSuchElementException.class)
//     public ResponseEntity<Map<String, String>> naoEncontrado(NoSuchElementException e) {
//         return ResponseEntity.status(404).body(Map.of("mensagem", e.getMessage()));
//     }
// }
