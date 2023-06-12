package com.example.artGallery.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "collector")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Collector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NonNull
    Integer id;

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
    @Column
    Integer archived;
}
