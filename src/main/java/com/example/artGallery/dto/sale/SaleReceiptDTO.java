package com.example.artGallery.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleReceiptDTO {
    private LocalDate dateOfSale;
    private int customerId;
    private String customerName;
    private String customerSurname;
    private String customerAddress;
    private String artworkTitle;
    private String type;
    private String medium;
    private String style;
    private String size;
    private int sellingPrice;
    private int spId;
    private String spName;
    private String spSurname;

}
