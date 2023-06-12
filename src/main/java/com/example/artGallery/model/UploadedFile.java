package com.example.artGallery.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "uploaded_files")
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NonNull
    int id;
    @Column
    String name;
    @Column
    String type;
    @Column(columnDefinition = "varbinary(max)")
    @Lob
    @NonNull
    private byte[] data;
}
