package com.example.todoapp.controller;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @GetMapping
    public ModelAndView index(Pageable pageable) {
        Map<String, Todo> model = new HashMap<>();
        List<Todo> response = repository.findAll(pageable).getContent();

        response.stream().forEach(t -> {
            model.put(t.getId().toString(), t);
        });

        return new ModelAndView("index", model);
    }
}
