//package com.example.artGallery.services;
//
//import com.example.artGallery.auth.IAuthenticationFacade;
//import com.example.artGallery.dto.LoginRequest;
//import com.example.artGallery.dto.user.UserCreateDTO;
//import com.example.artGallery.exc.LocalizedException;
//import com.example.artGallery.model.User;
//import com.example.artGallery.repositories.UserRepository;
//import com.example.artGallery.security.SecurityUtils;
//import com.example.artGallery.utils.ObjectMapperUtils;
//import io.github.jhipster.security.RandomUtil;
//import org.apache.tomcat.jni.Local;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//    private final Logger log = LoggerFactory.getLogger(UserService.class);
//
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//    private IAuthenticationFacade authenticationFacade;
//
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, IAuthenticationFacade authenticationFacade) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationFacade = authenticationFacade;
//    }
//
//    public boolean confirmPassword(String password) {
//        System.out.println("confirming");
//        Optional<String> username = SecurityUtils.getCurrentUserUsername();
//        if (username.isPresent()) {
//            Optional<User> user = userRepository.findByUsername(username.get());
//            if (!user.isPresent()) {
//                return false;
//            }
//            String currentEncryptedPassword = user.get().getPassword();
//            return passwordEncoder.matches(password, currentEncryptedPassword);
//        } else {
//            return false;
//        }
//
//    }
//
//    private void validateUsername(String username) throws LocalizedException {
//        if (username == null) throw new LocalizedException("error");
//        Optional<User> userByUsername = userRepository.findByUsername(username);
//        if (userByUsername.isPresent()) {
//            String currentUsername = SecurityUtils
//                    .getCurrentUserUsername()
//                    .orElseThrow(() -> new LocalizedException("Current user login not found"));
//            if (userByUsername.get().equals(userRepository.findByUsername(currentUsername).get()))
//                throw new LocalizedException(username);
//        }
//    }
//    private String getEncodedPassword(LoginRequest userAccountDTO) {
//        String password = userAccountDTO.getPassword();
//        return passwordEncoder.encode(password);
//    }
//
//
//    private String getEncodedPassword(User user) {
//        String password = user.getPassword();
//        return passwordEncoder.encode(password);
//    }
//
//    public User registerUser(UserCreateDTO userCreateDTO) {
//        userRepository
//                .findByUsername(userCreateDTO.getUsername().toLowerCase())
//                .ifPresent(
//                        existingUser -> {
//
//                            throw new LocalizedException(userCreateDTO.getUsername());
//
//                        }
//                );
//
//
//        String encryptedPassword = passwordEncoder.encode(userCreateDTO.getPassword());
//
//        User newUser = ObjectMapperUtils.map(userCreateDTO, new User());
//
//        newUser.setPassword(encryptedPassword);
//        newUser.setRole("ROLE_USER");
//        // new user is not active
//        userRepository.save(newUser);
//        log.debug("Created Information for User: {}", newUser);
//        return newUser;
//    }
//}
