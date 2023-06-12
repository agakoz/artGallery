package com.example.artGallery.dto.customer;

import com.example.artGallery.enums.ArtMedium;
import com.example.artGallery.enums.ArtStyle;
import com.example.artGallery.enums.ArtType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
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

    Integer prefArtistId;
    String prefArtistName;
    String prefArtistSurname;

    @NonNull
    String prefType;

    @NonNull
    String prefMedium;

    @NonNull
    String prefStyle;

    Long thisYearSelling;
    Long lastYearSelling;
}
