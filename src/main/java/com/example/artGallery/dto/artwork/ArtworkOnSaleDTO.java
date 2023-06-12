package com.example.artGallery.dto.artwork;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtworkOnSaleDTO {
    @NonNull
    int id;
    @NonNull
    private String title;
    @NonNull
    private int artistId;
    @NonNull
    private String artistName;
    @NonNull
    private String artistSurname;
    @NonNull
    private String artType;
    @NonNull
    private String artMedium;
    @NonNull
    private String artStyle;
    @NonNull
    private int askingPrice;
    @NonNull
    private String size;

    private Integer collectorId;
    private String collectorName;
    private String collectorSurname;
    private Integer fileId;
    private String fileName;


}
