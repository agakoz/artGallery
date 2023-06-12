package com.example.artGallery.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerPreferenceDTO {
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


    @NonNull
    String prefType;

    @NonNull
    String prefMedium;

    @NonNull
    String prefStyle;

    String prefArtistName;
    String prefArtistSurname;
}
