package com.example.artGallery.dto.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActiveArtistDTO {
    @NonNull
    int id;

    @NonNull
    String name;

    @NonNull
    String surname;

    @NonNull
    String address;

    @NonNull
    String phone;

    String ssn;

    @NonNull
    String usualType;

    @NonNull
    String usualMedium;

    @NonNull
    String usualStyle;

    Long thisYearSelling;
    Long lastYearSelling;
}
