package com.example.artGallery.model;

import com.example.artGallery.enums.ArtMedium;

import com.example.artGallery.enums.ArtStyle;
import com.example.artGallery.enums.ArtType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Artist {
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
    String address;

    @Column(nullable = false)
    @NonNull
    String phone;

    @Column
    String ssn;

    @NonNull
    @Column(name = "usual_type", nullable = false)
    String usualType;

    @NonNull
    @Column(name = "usual_medium", nullable = false)
    String usualMedium;

    @NonNull
    @Column(name = "usual_Style", nullable = false)
    String usualStyle;

    @NonNull
    @Column
    Integer archived;
}
