package com.example.artGallery.model;

import com.example.artGallery.enums.ArtMedium;
import com.example.artGallery.enums.ArtStyle;
import com.example.artGallery.enums.ArtType;
import lombok.*;
import org.hibernate.annotations.Loader;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Table(
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"artist_id", "title"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NonNull
    int id;

    @Column(nullable = false)
    @NonNull
    String size;

    @Column(nullable = false)
    @NonNull
    String title;

    @NonNull
    @Column(nullable = false, name = "art_type")
    String artType;

    @NonNull
    @Column(nullable = false, name = "art_medium")
    String artMedium;

    @NonNull
    @Column(nullable = false, name = "art_style")
    String artStyle;

    @Column(name = "asking_price", nullable = false)
    @NonNull
    int askingPrice;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "artist_id", referencedColumnName = "id", nullable = false)
    Artist artist;

    @ManyToOne
    @JoinColumn(name = "collector_id", referencedColumnName = "id", nullable = true)
    @Nullable
    Collector collector;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id", nullable = true)
    @Nullable
    UploadedFile file ;

}
