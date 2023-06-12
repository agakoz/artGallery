package com.example.artGallery.dto.collector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnerWorksDTO {
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String address;
    @NonNull
    private String artistName;
    @NonNull
    private String artistSurname;
    @NonNull
    private String workTitle;
    @NonNull
    private String type;
    @NonNull
    private String medium;
    @NonNull
    private String style;
    @NonNull
    private int askingPrice;
    private int sellingPrice = 0;
    private LocalDate saleDate = null;

}
