package com.example.artGallery.dto.artwork;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtworkUpdateDTO {
    Integer id;
    @NonNull
    String title;

    @NonNull
    String artType;

    @NonNull
    String artMedium;

    @NonNull
    String artStyle;

    @NonNull
    String size;

    @NonNull
    int askingPrice;

    @NonNull
    int artistId;

    Integer collectorId;

    Integer fileId;

}
