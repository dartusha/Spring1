package com.geekbrains.security;

import com.geekbrains.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerAuthService implements UserDetailsService {
private CustomerRepository customerRepository;

@Autowired
    public CustomerAuthService(CustomerRepository customerRepository){
    this.customerRepository=customerRepository;
}
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return customerRepository.getCustomerbyLogin(userName)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
