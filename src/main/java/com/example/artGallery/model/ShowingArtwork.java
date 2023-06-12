package com.example.artGallery.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "showing_artwork")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ShowingArtwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NonNull
    int id;

    @ManyToOne
    @JoinColumn(name = "artwork_id", referencedColumnName = "id", nullable = false)
    Artwork artwork;
}
