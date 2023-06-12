package com.example.artGallery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistTitleDTO {
    int artistId;
    String artworkTitle;
}
