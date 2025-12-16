package com.example.media.services;

import com.example.media.entities.User;
import com.example.media.repos.UserRepository;
import com.example.media.security.JWTUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JWTUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return JWTUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            return JWTUserDetails.create(foundUser);
        } else {
            return null;
        }
    }
}
