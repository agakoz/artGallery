package com.example.artGallery.dto.customer;

import com.example.artGallery.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateDTO {
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

    int prefArtist;

    @NonNull
    String prefType;

    @NonNull
    String prefMedium;

    @NonNull
    String prefStyle;
}
