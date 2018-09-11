package com.example.todoapp.controller;

import com.example.todoapp.domain.forms.TodoForm;
import com.example.todoapp.enums.State;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("todo", service.findAllByState(State.TODO));
        model.addObject("wip", service.findAllByState(State.WIP));
        model.addObject("done", service.findAllByState(State.DONE));
        return model;
    }

    @GetMapping("/create")
    public ModelAndView creationForm() {
        TodoForm todoForm = new TodoForm();
        return new ModelAndView("form")
                .addObject("todoForm", todoForm);
    }

    @PostMapping("/create")
    public ModelAndView save(@Valid TodoForm todoForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("form")
                    .addObject("todo", todoForm);
        }
        service.save(todoForm);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        try {
            TodoForm todoForm = service.findById(id);
            return new ModelAndView("edit").addObject("todo", todoForm);
        } catch (NoSuchElementException e) {
            return new ModelAndView("notFound");
        }
    }

    @PutMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid TodoForm todoForm,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("flash", "Error updating note");
            return new ModelAndView("redirect:/edit/" + id);
        }
        service.update(id, todoForm);
        return new ModelAndView("redirect:/");
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        service.delete(id);
        return new ModelAndView("redirect:/");
    }


}
