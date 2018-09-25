package com.example.todoapp.mapper;

import com.example.todoapp.domain.Account;
import com.example.todoapp.domain.forms.AccountVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "todos", ignore = true),
            @Mapping(target = "authorities", ignore = true)
    })
    Account voToAccount(AccountVO accountVO);
}
