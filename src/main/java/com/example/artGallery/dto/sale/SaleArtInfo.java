package com.example.artGallery.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleArtInfo {

    @NonNull
    private String artistName;
    @NonNull
    private String artistSurname;
    @NonNull
    String title;
    private Integer askingPrice;
    private Integer sellingPrice;

}
