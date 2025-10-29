package com.euflausino.desafiotodo.controller.todo;

import com.euflausino.desafiotodo.dto.AtualizaTodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoResponseDTO;
import com.euflausino.desafiotodo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TodoResponseDTO> criarTodo(@RequestBody @Valid TodoRequestDTO todoRequestDTO) {
       TodoResponseDTO todoResponseDTO = todoService.criarTodo(todoRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/todo/{id}")
                .buildAndExpand(todoResponseDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(todoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<TodoResponseDTO>> listarTodods() {
        return ResponseEntity.ok(todoService.listarTodos());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Page<TodoResponseDTO>> deletarTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.excluir(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TodoResponseDTO>  atualizarTodo( @PathVariable Long id, @RequestBody @Valid AtualizaTodoRequestDTO todoRequestDTO) {
        return ResponseEntity.ok(todoService.editarTodo(id,todoRequestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> getTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodo(id));
    }

}
