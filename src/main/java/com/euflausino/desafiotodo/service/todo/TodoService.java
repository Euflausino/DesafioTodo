package com.euflausino.desafiotodo.service.todo;

import com.euflausino.desafiotodo.dto.todo.AtualizaTodoRequestDTO;
import com.euflausino.desafiotodo.dto.todo.TodoRequestDTO;
import com.euflausino.desafiotodo.dto.todo.TodoResponseDTO;
import com.euflausino.desafiotodo.entity.todo.Todo;
import com.euflausino.desafiotodo.exception.todo.TodoNaoEncontradaException;
import com.euflausino.desafiotodo.mapper.todo.TodoMapper;
import com.euflausino.desafiotodo.repository.todo.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return  TodoMapper.toPageResponse(todoRepository.listarTodosComTrueNaFrente());
    }

    @Transactional
    public Page<TodoResponseDTO> excluir(Long id) {
        validarTodo(id);
        todoRepository.deleteById(id);
        return listarTodos();
    }

    @Transactional
    public TodoResponseDTO editarTodo(Long id, AtualizaTodoRequestDTO todoRequestDTO) {
        Todo todo = validarTodo(id);
        if(todoRequestDTO.nome() != null) todo.setNome(todoRequestDTO.nome());
        if(todoRequestDTO.descricao() != null) todo.setDescricao(todoRequestDTO.descricao());
        if(todoRequestDTO.prioridade() != null) todo.setPrioridade(todoRequestDTO.prioridade());
        if (todoRequestDTO.realizado()) todo.setRealizado(true);

        return TodoMapper.todoResponseDTO(todo);
    }

    public TodoResponseDTO getTodo(Long id) {
        Todo todo = validarTodo(id);
        return TodoMapper.todoResponseDTO(todo);
    }

    private Todo validarTodo(Long id) {
       return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNaoEncontradaException("Todo não encontrada."));

    }

}
