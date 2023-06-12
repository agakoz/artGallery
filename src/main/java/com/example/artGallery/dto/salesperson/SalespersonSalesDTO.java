package com.example.artGallery.dto.salesperson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalespersonSalesDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    private Long totalSales;
}
