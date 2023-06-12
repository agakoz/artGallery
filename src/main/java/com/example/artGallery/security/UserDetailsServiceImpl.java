//package com.example.artGallery.security;
//
//
//import com.example.artGallery.model.User;
//import com.example.artGallery.repositories.UserRepository;
//import lombok.SneakyThrows;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    UserRepository userRepository;
//
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//
//        if (user.isEmpty())
//            throw new UsernameNotFoundException(String.format("User with username %s not found", username));
//        return user.get();
//
//    }
//}