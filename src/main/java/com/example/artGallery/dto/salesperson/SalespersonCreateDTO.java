package com.example.artGallery.dto.salesperson;

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
public class SalespersonCreateDTO {
    @NonNull
    String name;

    @NonNull
    String surname;

    @NonNull
    String ssn;
}
