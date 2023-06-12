package com.example.artGallery.repositories;

import com.example.artGallery.dto.NameAddressDTO;
import com.example.artGallery.dto.customer.CustomerDTO;
import com.example.artGallery.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT new com.example.artGallery.dto.customer.CustomerDTO" +
            "(c.id, c.name, c.surname, c.address, c.phone, a.id, a.name, a.surname, c.prefType, c.prefMedium, c.prefStyle, SUM(ty.sellingPrice), SUM(ly.sellingPrice)) " +
            " FROM Customer c " +
            "left join Artist a on a.id = c.artist.id " +
            "left JOIN Sale ty on ty.customer.id = c.id " +
            "and ty.date >= :lastYearEnd " +
            "left JOIN Sale ly on ly.customer.id = c.id " +
            "and ly.date <= :lastYearEnd and ly.date >= :lastYearBegin " +
            " where c.archived =0" +
            " group by c.id, c.name, c.surname, c.address, c.phone, a.id, a.name, a.surname, c.prefType, c.prefMedium, c.prefStyle" +
            " order by c.name")
    List<CustomerDTO> retrieveCustomerDTO(LocalDate lastYearBegin, LocalDate lastYearEnd);

//    @Query("SELECT new com.example.artGallery.dto.customer.CustomerDTO" +
//            "(a.id, a.name, a.surname, a.address, a.phone,  a.prefType, a.prefMedium, a.prefStyle) FROM Customer a WHERE a.id = :customerId")
//    Optional<CustomerDTO> retrieveCustomerInfoDTO(int customerId);
    @Query("SELECT c.id from Customer c where c.name = :name and c.surname = :surname and c.address = :address")
    Optional<Integer> findByNameAddress(String name, String surname, String address);

    @Query("SELECT new com.example.artGallery.dto.NameAddressDTO" +
            "(a.id, a.name, a.surname, a.address) FROM Customer a where a.archived = 0")
    List<NameAddressDTO> retrieveBasicInfo();

}
