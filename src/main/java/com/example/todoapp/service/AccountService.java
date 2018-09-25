package com.example.todoapp.service;

import com.example.todoapp.domain.Account;
import com.example.todoapp.domain.forms.AccountVO;
import com.example.todoapp.exceptions.EmailAlreadyExistsException;
import com.example.todoapp.mapper.AccountMapper;
import com.example.todoapp.repository.AccountRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void registerNewAccount(AccountVO accountVO) {
        Account account = accountRepository.findByUsername(accountVO.getUsername()).get();
        if (account != null) {
            throw new EmailAlreadyExistsException();
        }
        Account toPersist = accountMapper.voToAccount(accountVO);
        accountRepository.save(account);
    }
}
