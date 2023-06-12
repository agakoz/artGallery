package com.example.artGallery.dto.sale;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleCreateDTO {
    @NonNull
    private int artworkId;
    @NonNull
    private int customerId;

    @NonNull
    private int salespersonId;

    @NonNull
    private LocalDate date;

    @NonNull
    private int sellingPrice;
}
