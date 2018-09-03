package com.example.todoapp.domain.request;

import com.example.todoapp.enums.State;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TodoRequest {

    @NotNull
    private String title;

    private String text;

    private State state;
}
