package com.example.artGallery.repositories;

import com.example.artGallery.dto.sale.*;
import com.example.artGallery.model.Sale;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
//
public interface SaleRepository extends JpaRepository<Sale, Integer> {
//    @Query("SELECT new com.example.artGallery.dto.sale.SaleDTO" +
//            "(a.id, a.artist.id, a.customer.id, a.collector.id, a.salesperson.id,  a.date, a.sellingPrice, a.artwork.askingPrice) FROM Sale a")
//    List<SaleDTO> retrieveSaleDTO();
////    @Query("SELECT new com.example.artGallery.dto.sale.SaleDTO" +
////            "(a.id, a.artist.id, a.customer.id, a.collector.id, a.salesperson.id,  a.date, a.sellingPrice) FROM Sale a WHERE a.id = :saleId")
////    Optional<SaleDTO> retrieveSaleInfoDTO(int saleId);

    @Query("SELECT new com.example.artGallery.dto.sale.SaleArtInfo" +
            "(s.artwork.artist.name, s.artwork.artist.surname, s.artwork.title, s.artwork.askingPrice, s.sellingPrice) " +
            "FROM Sale s " +
            "WHERE s.salesperson.id = :spId AND s.date >= :begin AND s.date <= :end ")
    List<SaleArtInfo> getSalesFromSalespersonInPeriod(int spId, String begin, String end);

    @Query("SELECT new com.example.artGallery.dto.sale.SaleExtendedInfoDTO" +
            "(s.id, " +
            "s.artwork.id, s.artwork.title, " +
            "s.artwork.artist.id, s.artwork.artist.name, s.artwork.artist.surname, " +
            "c.id, c.name, c.surname, " +
            "s.customer.id, s.customer.name, s.customer.surname, " +
            "s.salesperson.id, s.salesperson.name, s.salesperson.surname, " +
            "s.date, s.sellingPrice) " +
            "FROM Sale s left join Collector c on c.id = s.artwork.collector.id" +
//            "LEFT JOIN Collector col on col.id = s.artwork.collector.id" +
            " ")
    List<SaleExtendedInfoDTO> getAllPastSales();

    @Query("SELECT new com.example.artGallery.dto.sale.SaleExtendedInfoDTO" +
            "(s.id, " +
            "aw.id, aw.title, " +
            "a.id, a.name, a.surname, " +
            "col.id, col.name, col.surname, " +
            "cus.id, cus.name, cus.surname, " +
            "sp.id, sp.name, sp.surname, " +
            "s.date, s.sellingPrice) " +
            "FROM Sale s " +
            "JOIN Artwork aw on s.artwork.id = aw.id " +
            "JOIN Artist a on a.id = aw.artist.id " +
            "JOIN Collector col on col.id = aw.collector.id " +
            "JOIN Customer cus on cus.id = s.customer.id " +
            "JOIN Salesperson sp on sp.id = s.salesperson.id " +
            "WHERE s.date >= :date")
    List<SaleExtendedInfoDTO> retrieveSaleInfoSinceGivenDate(LocalDate date);

    @Query("SELECT new com.example.artGallery.dto.sale.SaleReceiptDTO(" +
            "s.date, s.customer.id, s.customer.name, s.customer.surname, s.customer.address, " +
            "s.artwork.title, s.artwork.artType, s.artwork.artMedium, s.artwork.artStyle, s.artwork.size, " +
            "s.sellingPrice, s.salesperson.id, s.salesperson.name, s.salesperson.surname)" +
            "FROM Sale s " +
            "WHERE s.id = :saleId")
    Optional<SaleReceiptDTO> getReceiptInfo(int saleId);

    @Query("SELECT new com.example.artGallery.dto.sale.PaymentStubDTO(" +
            "s.artwork.artist.name, s.artwork.artist.surname, s.artwork.artist.address, s.artwork.artist.ssn, " +
            "s.artwork.title, s.artwork.artType, s.artwork.artMedium, s.artwork.artStyle, s.artwork.size, " +
            "s.salesperson.name, s.salesperson.surname, s.sellingPrice, s.artwork.askingPrice-s.sellingPrice)" +
            "FROM Sale s " +
            "WHERE s.id = :saleId")
    Optional<PaymentStubDTO> getPaymentStub(int saleId);

    @Query("select s.id from Sale s where s.artwork.artist.id = :artistId")
    List<Integer> findIdsByArtistId(int artistId);

    @Query("select s.id from Sale s where s.salesperson.id = :id")
    List<Integer> findIdsBySalespersonId(int id);

    @Query("SELECT new com.example.artGallery.dto.sale.WeeklySales" +
            "(s.salesperson.id, s.salesperson.name, s.salesperson.surname) FROM Sale s WHERE s.date between :monday and :today")
    List<WeeklySales> getInfoForWeekSales(LocalDate monday, LocalDate today);

    @Query("SELECT new com.example.artGallery.dto.sale.SaleDTO(" +
            "s.artwork.id, s.artwork.title, s.artwork.artist.name, s.artwork.artist.surname, c.name, c.surname, s.customer.name, s.customer.surname, s.customer.address, s.sellingPrice, s.artwork.askingPrice, s.date ) " +
            "from Sale s left join Collector c on s.artwork.collector.id = c.id " +
            "where s.salesperson.id = :salespersonId and s.date between :monday and :today")
    List<SaleDTO> getSale(Integer salespersonId, LocalDate monday, LocalDate today);
}
