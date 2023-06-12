package com.example.artGallery.dto.artist;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class ArtistWorksDTO {
    @NonNull
    private String title;
    @NonNull
    private String artType;
    @NonNull
    private String artMedium;
    @NonNull
    private String artStyle;
    @NonNull
    private int askingPrice;
    private Integer sellingPrice;
    private LocalDate date;

}
