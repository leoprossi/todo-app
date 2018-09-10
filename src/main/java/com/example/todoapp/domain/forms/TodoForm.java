package com.example.todoapp.domain.forms;

import com.example.todoapp.enums.State;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TodoForm {

    private Long id;

    @NotEmpty(message = "Title is a mandatory field")
    private String title;

    private String text;

    private State state;
}
