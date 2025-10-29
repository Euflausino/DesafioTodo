package com.euflausino.desafiotodo.service;

import com.euflausino.desafiotodo.dto.AtualizaTodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoResponseDTO;
import com.euflausino.desafiotodo.entity.Todo;
import com.euflausino.desafiotodo.exception.TodoNaoEncontradaException;
import com.euflausino.desafiotodo.mapper.TodoMapper;
import com.euflausino.desafiotodo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //criar, vizualizar, editar e excluir
    @Transactional
    public TodoResponseDTO criarTodo(TodoRequestDTO todoRequestDTO) {
        Todo todo = TodoMapper.toEntity(todoRequestDTO);
        todoRepository.save(todo);
        return TodoMapper.todoResponseDTO(todo);
    }

    public Page<TodoResponseDTO> listarTodos() {
        return  TodoMapper.todoResponsePageDTO(todoRepository.listarTodosComTrueNaFrente());
    }

    @Transactional
    public Page<TodoResponseDTO> excluir(Long id) {
        validaTodo(id);
        todoRepository.deleteById(id);
        return listarTodos();
    }

    @Transactional
    public TodoResponseDTO editarTodo(Long id, AtualizaTodoRequestDTO todoRequestDTO) {
        Todo todo =  validaTodo(id);

        if(todoRequestDTO.nome() != null) todo.setNome(todoRequestDTO.nome());
        if(todoRequestDTO.descricao() != null) todo.setDescricao(todoRequestDTO.descricao());
        if(todoRequestDTO.prioridade() != null) todo.setPrioridade(todoRequestDTO.prioridade());
        if (todoRequestDTO.realizado()) todo.setRealizado(true);

        return TodoMapper.todoResponseDTO(todo);
    }

    public TodoResponseDTO getTodo(Long id) {

        return TodoMapper.todoResponseDTO(validaTodo(id));
    }

    private Todo validaTodo(Long id){
        return todoRepository.findById(id).orElseThrow(() -> new TodoNaoEncontradaException("Todo não encontrada"));
    }

}
