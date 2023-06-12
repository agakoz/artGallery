//package com.example.artGallery.model;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.Collections;
//
//@Entity
//@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
//@Table(name = "users")
//public class User implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
//    @NonNull
//    int id;
//
//    @Column(nullable = false, length = 255, unique = true)
//    @NonNull
//    private String username;
//
//    @NonNull
//    @Column(nullable = false, length = 255)
//    private String password;
//
//    @NonNull
//    @Column(nullable = false, length = 255)
//    private String role;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        return Collections.singleton(new SimpleGrantedAuthority(role));
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}