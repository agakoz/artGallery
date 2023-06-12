package com.example.artGallery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sale")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NonNull
    int id;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "artwork_id", referencedColumnName = "id", nullable = false)
    Artwork artwork;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    Customer customer;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "salesperson_id", referencedColumnName = "id", nullable = false)
    Salesperson salesperson;

    @Column(name = "sale_date", nullable = false)
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "selling_price", nullable = false)
    @NonNull
    int sellingPrice;
}
