package com.example.todoapp.repository;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByState(State state);
}
