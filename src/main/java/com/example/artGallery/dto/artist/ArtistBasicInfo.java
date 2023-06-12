package com.example.artGallery.dto.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistBasicInfo {
    int id;
    String ssn;
    String name;
    String surname;
}
