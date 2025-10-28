package com.euflausino.desafiotodo.mapper.todo;

import com.euflausino.desafiotodo.dto.todo.TodoRequestDTO;
import com.euflausino.desafiotodo.dto.todo.TodoResponseDTO;
import com.euflausino.desafiotodo.entity.todo.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class TodoMapper {

    public static Todo toEntity(TodoRequestDTO todoRequestDTO) {
        return new Todo(
                todoRequestDTO.nome(),
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

    public static Page<TodoResponseDTO> toPageResponse(List<Todo> list){
        List<TodoResponseDTO> retorno = list.stream().map(TodoMapper::todoResponseDTO).toList();
        return new PageImpl<>(retorno);
    }
}
