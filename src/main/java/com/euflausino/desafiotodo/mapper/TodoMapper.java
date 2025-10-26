package com.euflausino.desafiotodo.mapper;

import com.euflausino.desafiotodo.dto.TodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoResponseDTO;
import com.euflausino.desafiotodo.entity.Todo;

import java.util.List;

public class TodoMapper {

    public static Todo toEntity(TodoRequestDTO todoRequestDTO) {
        return new Todo(
                todoRequestDTO.name(),
                todoRequestDTO.descricao(),
                todoRequestDTO.prioridade()
        );
    }

    public static TodoResponseDTO todoResponseDTO(Todo entity) {
        return new TodoResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.isRealizado(),
                entity.getPrioridade()
        );
    }

    public static List<TodoResponseDTO> todoResponseListDTO(List<Todo> list) {
        return list.stream().map(TodoMapper::todoResponseDTO).toList();
    }

}
