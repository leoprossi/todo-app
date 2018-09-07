package com.example.todoapp.service;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.domain.request.TodoForm;
import com.example.todoapp.enums.State;
import com.example.todoapp.mapper.TodoMapper;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    @Autowired
    private TodoMapper mapper;

    @Autowired
    private TodoRepository repository;

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return repository.findAll();
    }

    public List<Todo> findAllByState(State state) {
        return repository.findAllByState(state);
    }

    @Transactional(readOnly = true)
    public TodoForm findById(Long id) {
        Todo todo = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return mapper.todoToRequest(todo);
    }

    @Transactional
    public void save(TodoForm todoForm) {
        repository.save(mapper.requestToTodo(todoForm));
    }

    @Transactional
    public void update(Long id, TodoForm todoForm) {
        Todo todo = mapper.requestToTodo(todoForm);
        todo.setId(id);
        repository.save(todo);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
