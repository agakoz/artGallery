package com.example.artGallery.model;

import com.example.artGallery.enums.ArtMedium;
import com.example.artGallery.enums.ArtStyle;
import com.example.artGallery.enums.ArtType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
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

    @ManyToOne
    @JoinColumn(name = "pref_artist", referencedColumnName = "id")
    Artist artist;

    @NonNull
    @Column(name = "pref_type", nullable = false)
    String prefType;

    @NonNull
    @Column(name = "pref_medium", nullable = false)
    String prefMedium;

    @NonNull
    @Column(name = "pref_style", nullable = false)
    String prefStyle;

    @NonNull
    @Column
    int archived;
}
