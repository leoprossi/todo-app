package com.example.todoapp.service;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.domain.request.TodoRequest;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return repository.findAll();
    }

    public void save(TodoRequest todo) {
        //Implement stuff here
    }
}
