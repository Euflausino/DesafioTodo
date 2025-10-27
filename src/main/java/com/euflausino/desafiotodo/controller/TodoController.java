package com.euflausino.desafiotodo.controller;

import com.euflausino.desafiotodo.dto.AtualizaTodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoResponseDTO;
import com.euflausino.desafiotodo.mapper.TodoMapper;
import com.euflausino.desafiotodo.service.TodoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<List<TodoResponseDTO>> criarTodo(@RequestBody @Valid TodoRequestDTO todoRequestDTO, UriComponentsBuilder uriBuilder) {

        List<TodoResponseDTO> todoResponseDTO = todoService.criarTodo(todoRequestDTO);
        var uri = uriBuilder.path("/todo/{id}").buildAndExpand(todoRequestDTO).toUri();
        return ResponseEntity.created(uri).body(todoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> listarTodods() {
        return ResponseEntity.ok(todoService.listarTodos());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<List<TodoResponseDTO>> deletarTodo(@PathVariable Long id) {
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
