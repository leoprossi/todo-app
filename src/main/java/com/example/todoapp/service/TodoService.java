package com.example.todoapp.service;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.domain.forms.TodoForm;
import com.example.todoapp.enums.State;
import com.example.todoapp.mapper.TodoMapper;
import com.example.todoapp.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoMapper mapper;

    @Autowired
    private TodoRepository repository;

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        log.info("Method = findAll()");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Todo> findAllByState(State state) {
        log.info("Method = findAllByState(), state = {}", state);
        return repository.findAllByState(state);
    }

    @Transactional(readOnly = true)
    public TodoForm findById(Long id) {
        log.info("Method = findById(), id = {}", id);

        Todo todo = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return mapper.todoToRequest(todo);
    }

    @Transactional
    public void save(TodoForm todoForm) {
        log.info("Method = save(), todoForm = {}", todoForm);
        repository.save(mapper.requestToTodo(todoForm));
    }

    @Transactional
    public void update(Long id, TodoForm todoForm) {
        log.info("Method = update(), id = {}, todoForm = {}", id, todoForm);

        Todo todo = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        mapper.requestToTodo(todo, todoForm);
        repository.save(todo);
    }

    @Transactional
    public void moveTo(Long id, State state) {
        log.info("Method = moveTo(), id = {}, state = {}", id, state);

        Todo todo = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        todo.setState(state);
        repository.save(todo);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Method = delete(), id = {}", id);
        repository.deleteById(id);
    }
}
