package com.example.artGallery.repositories;

import com.example.artGallery.dto.sale.SaleArtInfo;
import com.example.artGallery.dto.sale.WeeklySales;
import com.example.artGallery.dto.salesperson.SalespersonDTO;
import com.example.artGallery.model.Salesperson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SalespersonRepository extends JpaRepository<Salesperson, Integer> {
    @Query("SELECT new com.example.artGallery.dto.salesperson.SalespersonDTO" +
            "(a.id, a.name, a.surname, a.ssn) FROM Salesperson a where a.archived =0")
    List<SalespersonDTO> retrieveSalespersonDTO();

    @Query("SELECT new com.example.artGallery.dto.salesperson.SalespersonDTO" +
            "(a.id, a.name, a.surname, a.ssn) FROM Salesperson a WHERE a.id = :salespersonId")
    Optional<SalespersonDTO> retrieveSalespersonInfoDTO(int salespersonId);

    @Query("SELECT s.id from Salesperson s where s.ssn = :ssn")
    Optional<Integer> findBySSN(String ssn);

    @Query("select sum(s.sellingPrice) from Sale s where s.salesperson.id = :salespersonId and s.date between :begin and :end ")
    List<Integer> salesAmtInPeriod(int salespersonId, LocalDate begin, LocalDate end);

    @Query("SELECT new com.example.artGallery.dto.sale.SaleArtInfo" +
            "(s.artwork.artist.name, s.artwork.artist.surname, s.artwork.title, s.artwork.askingPrice, s.sellingPrice) FROM Sale s WHERE s.salesperson.id = :salespersonId and s.date between :begin and :end")
    List<SaleArtInfo> getSoldArt(int salespersonId, LocalDate begin, LocalDate end);



//    @Query("SELECT new com.example.artGallery.dto.salesperson.SalespersonPerformanceDTO" +
//            "(:begin, :end, sp.name, sp.surname, sum(s.sellingPrice)) " +
//            "FROM Salesperson sp  JOIN Sale s on s.salesperson.id = sp.id " +
//            "WHERE s.id = :salespersonId AND s.date >= :begin AND s.date <= :end " +
//            "GROUP BY :begin, :end, sp.name, sp.surname")
//    Optional<SalespersonPerformanceDTO> retrieveSalespersonPerformanceInPeriod(int salespersonId, LocalDate begin, LocalDate end);
}
