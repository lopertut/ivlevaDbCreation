package com.lopertut.dbcreation.services;

import com.lopertut.dbcreation.entity.CustomUserDetails;
import com.lopertut.dbcreation.entity.User;
import com.lopertut.dbcreation.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        user.setPassword(user.getPassword().trim());
        System.out.println(user.getUsername() + ' ' + user.getEmail() + ' ' + user.getPassword());
        return CustomUserDetails.build(user);
    }
}
