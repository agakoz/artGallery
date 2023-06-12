//package com.example.artGallery.controllers;
//
//import com.example.artGallery.dto.JwtResponse;
//import com.example.artGallery.dto.LoginRequest;
//import com.example.artGallery.dto.PasswordDTO;
//import com.example.artGallery.dto.user.UserCreateDTO;
//import com.example.artGallery.exc.LocalizedException;
//import com.example.artGallery.model.User;
//import com.example.artGallery.security.JwtUtils;
//import com.example.artGallery.services.UserService;
//import com.example.artGallery.utils.ObjectMapperUtils;
//import lombok.SneakyThrows;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.transaction.Transactional;
//
//@CrossOrigin(origins = "http://localhost:4200")
//@Controller
//@RequestMapping("/auth")
//public class AuthController {
//    private final UserService userService;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtils jwtUtils;
//
//    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
//        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//        this.jwtUtils = jwtUtils;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        User userDetails = (User) authentication.getPrincipal();
////        List<String> roles = userDetails.getAuthorities().stream()
////                .map(item -> item.getAuthority())
////                .collect(Collectors.toList());
//
//        JwtResponse jwtResponse = ObjectMapperUtils.map(userDetails, new JwtResponse());
//        jwtResponse.setToken(jwt);
//        return ResponseEntity.ok(jwtResponse);
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//
//        } catch (LocalizedException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }
//
//    @PostMapping("/confirmPassword")
//    public ResponseEntity<Object> checkPassword(@RequestBody PasswordDTO passwordDTO) {
//        String password = passwordDTO.getPassword();
//        boolean confirmed = userService.confirmPassword(password);
//        return new ResponseEntity<>(confirmed, HttpStatus.OK);
//    }
//
//    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    @SneakyThrows
//    public void registerAccount(@RequestBody UserCreateDTO userCreateDTO) {
////        System.out.println("rejestracja");
//        System.out.println(userCreateDTO);
//        User user = userService.registerUser(userCreateDTO);
//    }
//}
