package com.example.artGallery.dto.sale;

import com.example.artGallery.model.Artist;
import com.example.artGallery.model.Collector;
import com.example.artGallery.model.Customer;
import com.example.artGallery.model.Salesperson;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    @NonNull
    private int artworkId;
    @NonNull
    private String artworkTitle;
    @NonNull
    private String artistName;
    @NonNull
    private String artistSurname;
    private String collectorName;
    private String collectorSurname;
    @NonNull
    private String customerName;
    @NonNull
    private String customerSurname;
    @NonNull
    private String customerAddress;
    @NonNull
    private int sellingPrice;
    @NonNull
    private int askingPrice;
    @NonNull
    private LocalDate date;
}
