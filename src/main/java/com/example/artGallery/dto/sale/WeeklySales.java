package com.example.artGallery.dto.sale;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class WeeklySales {
    @NonNull
    Integer salespersonId;
    @NonNull
    String salespersonName;
    @NonNull
    String salespersonSurname;
    List<SaleDTO> sales;
}
