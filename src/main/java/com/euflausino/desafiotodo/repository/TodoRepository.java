package com.euflausino.desafiotodo.repository;

import com.euflausino.desafiotodo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // public List<Todo> findByRealizadoOrderByPrioridadeAscIdAsc(boolean status); futura emplementacao

    @Query("SELECT t FROM Todo t " +
            "ORDER BY CASE WHEN t.realizado = true THEN 0 ELSE 1 END, t.prioridade ASC, t.id ASC")
    List<Todo> listarTodosComTrueNaFrente();

}
