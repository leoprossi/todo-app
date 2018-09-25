package com.example.todoapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The email address you are trying to register is already registered.")
public class EmailAlreadyExistsException extends RuntimeException {
}
