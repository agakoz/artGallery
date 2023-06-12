package com.example.artGallery.dto.collector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollectorOwnerDTO {
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
    Long thisYearSelling;
    Long lastYearSelling;
}
