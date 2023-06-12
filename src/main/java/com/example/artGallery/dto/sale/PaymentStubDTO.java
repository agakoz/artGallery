package com.example.artGallery.dto.sale;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PaymentStubDTO {
    @NonNull
    private String artistName;
    @NonNull
    private String artistSurname;
    @NonNull
    private String artistAddress;
    @NonNull
    private String artistSSN;
    @NonNull
    private String artworkTitle;
    @NonNull
    private String type;
    @NonNull
    private String medium;
    @NonNull
    private String style;
    @NonNull
    private String size;
    @NonNull
    private String salespersonName;
    @NonNull
    private String salespersonSurname;
    @NonNull
    private int sellingPrice;
    private int remittedAmount;
}
