package com.example.artGallery.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesCreateDTO {
    @NonNull
    int artistId;

    @NonNull
    int customerId;

    int collectorId;

   @NonNull
    int salespersonId;

    @NonNull
    private LocalDate date;

    @NonNull
    int sellingPrice;

}
