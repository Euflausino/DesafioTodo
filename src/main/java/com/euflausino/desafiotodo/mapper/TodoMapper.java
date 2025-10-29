package com.euflausino.desafiotodo.mapper;

import com.euflausino.desafiotodo.dto.TodoRequestDTO;
import com.euflausino.desafiotodo.dto.TodoResponseDTO;
import com.euflausino.desafiotodo.entity.Todo;
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

    public static Page<TodoResponseDTO> todoResponsePageDTO(List<Todo> list) {
        List<TodoResponseDTO> retorno = list.stream().map(TodoMapper::todoResponseDTO).toList();
        return  new PageImpl<>(retorno);
    }

}
