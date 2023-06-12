package com.example.artGallery.model;

import com.example.artGallery.enums.ArtMedium;
import com.example.artGallery.enums.ArtStyle;
import com.example.artGallery.enums.ArtType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "salesperson")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salesperson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NonNull
    int id;

    @Column(nullable = false)
    @NonNull
    String name;

    @Column(nullable = false)
    @NonNull
    String surname;

    @Column(nullable = false)
    @NonNull
    String ssn;

    @Column
    @NonNull
    int archived;
}
