package com.example.artGallery.dto.sale;

import io.swagger.models.auth.In;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class SaleExtendedInfoDTO {
    @NonNull
    int id;

    @NonNull
    int artwork_id;

    @NonNull
    String artworkTitle;

    @NonNull
    int artistId;

    @NonNull
    String artistName;

    @NonNull
    String artistSurname;

    Integer collectorId;
    String collectorName;
    String collectorSurname;

    @NonNull
    int customerId;
    @NonNull
    String customerName;
    @NonNull
    String customerSurname;

    @NonNull
    int salespersonId;

    @NonNull
    String spName;

    @NonNull
    String spSurname;

    @NonNull
    private LocalDate date;

    @NonNull
    int sellingPrice;
}
