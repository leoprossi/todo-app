package com.example.todoapp.domain.forms;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AccountVO {

    @Email(message = "Username must be a valid email address.")
    @NotBlank(message = "The field 'email' can't be blank.")
    private String username;

    @NotBlank(message = "The field 'name' can't be blank.")
    private String name;

    @Size(min = 6, message = "Your password must contain at least 6 characters.")
    private String password;
    private String confirmPassword;

    @AssertTrue
    public boolean isBothPasswordsMatching() {
        return password.equals(confirmPassword);
    }
}
