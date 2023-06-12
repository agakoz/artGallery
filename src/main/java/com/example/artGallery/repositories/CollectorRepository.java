package com.example.artGallery.repositories;

import com.example.artGallery.dto.collector.CollectorDTO;
import com.example.artGallery.dto.collector.CollectorOwnerDTO;
import com.example.artGallery.dto.collector.OwnerWorksDTO;
import com.example.artGallery.model.Collector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CollectorRepository extends JpaRepository<Collector, Integer> {
    @Query("SELECT new com.example.artGallery.dto.collector.CollectorDTO" +
            "(a.id, a.name, a.surname, a.address, a.phone, a.ssn) FROM Collector a where a.archived =0")
    List<CollectorDTO> retrieveCollectorsDTO();
    @Query("SELECT new com.example.artGallery.dto.collector.CollectorDTO" +
            "(a.id, a.name, a.surname, a.address, a.phone, a.ssn) FROM Collector a WHERE a.id = :collectorId and a.archived =0")
    Optional<CollectorDTO> retrieveCollectorBasicInfoDTO(int collectorId);

    @Query("SELECT new com.example.artGallery.dto.collector.CollectorOwnerDTO" +
            "(a.id, a.name, a.surname, a.address, a.phone, a.ssn, " +
            "SUM(st.sellingPrice), SUM(sl.sellingPrice)) " +
            "FROM Collector a join Sale st on a.id = st.artwork.artist.id " +
            "join Sale sl on st.id = sl.id " +
            "where st.date >= :lastYearEnd and st.artwork.collector.archived =0 " +
            " and st.date between :lastYearBegin and :lastYearEnd " +
            "group by a.id, a.name, a.surname, a.address, a.ssn, a.phone")
    List<CollectorOwnerDTO> retrieveCollectorOwner(LocalDate lastYearBegin, LocalDate lastYearEnd);

    @Query("SELECT new com.example.artGallery.dto.collector.OwnerWorksDTO" +
            "(c.name, c.surname, c.address, aw.artist.name, aw.artist.surname, aw.title, aw.artType, aw.artMedium, aw.artStyle, aw.askingPrice, s.sellingPrice, s.date)" +
            "FROM Collector c " +
            "JOIN Artwork aw on aw.collector.id = c.id " +
            "LEFT JOIN Sale s on aw.id = s.artwork.artist.id " +
            "WHERE s.artwork.collector.name is not null")
    List<OwnerWorksDTO> retrieveCollectorsWorks(int collectorId);
    @Query("SELECT c.id from Collector c where c.ssn = :ssnValue")
    Optional<Integer> findBySSN(String ssnValue);
}
