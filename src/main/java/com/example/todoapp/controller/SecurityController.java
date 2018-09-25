package com.example.todoapp.controller;

import com.example.todoapp.domain.forms.AccountVO;
import com.example.todoapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("/register")
                .addObject("account", new AccountVO());
    }

    @PostMapping("/register")
    public ModelAndView newAccount(@Valid AccountVO accountVO,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/register")
                    .addObject("account", accountVO);
        }
        accountService.registerNewAccount(accountVO);
        return new ModelAndView("redirect:/login");
    }

}
