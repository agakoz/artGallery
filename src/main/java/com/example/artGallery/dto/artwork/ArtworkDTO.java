package com.example.artGallery.dto.artwork;

import com.example.artGallery.enums.ArtMedium;
import com.example.artGallery.enums.ArtStyle;
import com.example.artGallery.enums.ArtType;
import com.example.artGallery.model.Artist;
import com.example.artGallery.model.Collector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtworkDTO {
    @NonNull
    int id;

    @NonNull
    String title;

    @NonNull
    String artType;

    @NonNull
    String artMedium;

    @NonNull
    String artStyle;

    @NonNull
    int askingPrice;

    @NonNull
    int artistId;

    @NonNull
    int collectorId;

}
