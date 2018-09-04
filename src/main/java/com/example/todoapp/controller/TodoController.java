package com.example.todoapp.controller;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.domain.request.TodoRequest;
import com.example.todoapp.repository.TodoRepository;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/")
    public ModelAndView index() {
        List<Todo> response = service.findAll();
        return new ModelAndView("index")
                .addObject("todos", response);
    }

    @GetMapping("/create")
    public ModelAndView addForm() {
        TodoRequest todo = new TodoRequest();
        return new ModelAndView("form")
                .addObject("todo", todo);
    }

    @PostMapping("/create")
    public ModelAndView save(@Valid TodoRequest todo) {
        service.save(todo);
        return index();
    }
}
