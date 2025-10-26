package com.euflausino.desafiotodo.service;

import com.euflausino.desafiotodo.dto.AtualizaTodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoResponseDTO;
import com.euflausino.desafiotodo.entity.Todo;
import com.euflausino.desafiotodo.exception.NumeroNaoEncontradoException;
import com.euflausino.desafiotodo.mapper.TodoMapper;
import com.euflausino.desafiotodo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //criar, vizualizar, editar e excluir

    public List<TodoResponseDTO> criarTodo(TodoRequestDTO todoRequestDTO) {
        Todo todo = TodoMapper.toEntity(todoRequestDTO);
        todoRepository.save(todo);
        return listarTodos();
    }

    public List<TodoResponseDTO> listarTodos() {
        return  TodoMapper.todoResponseListDTO(todoRepository.listarTodosComTrueNaFrente());
    }

    public List<TodoResponseDTO> excluir(Long id) {
        if(id == null || id <= 0 ) {
             throw new IllegalArgumentException("Numero inválido ou não cadastrado.");
        }
        if(!todoRepository.existsById(id)){
            throw new NumeroNaoEncontradoException("Numero não encontrado.");
        }
        todoRepository.deleteById(id);
        return listarTodos();
    }

    public TodoResponseDTO editarTodo(Long id, AtualizaTodoRequestDTO todoRequestDTO) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Numero inválido.");
        }
        if(!todoRepository.existsById(id)){
            throw new NumeroNaoEncontradoException("Numero não encontrado.");
        }
        Todo todo = todoRepository.getReferenceById(id);
        todo.atualizarInformacoes(todoRequestDTO);
        return TodoMapper.todoResponseDTO(todo);
    }

}
