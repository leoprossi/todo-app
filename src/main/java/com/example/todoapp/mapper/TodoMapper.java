package com.example.todoapp.mapper;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.domain.forms.TodoForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Todo requestToTodo(TodoForm request);

    TodoForm todoToRequest(Todo todo);
}
