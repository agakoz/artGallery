package com.example.artGallery.repositories;

import com.example.artGallery.dto.artist.ActiveArtistDTO;
import com.example.artGallery.dto.artist.ArtistBasicInfo;
import com.example.artGallery.dto.artist.ArtistDTO;
import com.example.artGallery.dto.artist.ArtistWorksDTO;
import com.example.artGallery.model.Artist;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    @Query("SELECT new com.example.artGallery.dto.artist.ArtistDTO" +
            "(a.id, a.name, a.surname, a.address, a.phone, a.ssn, a.usualType, a.usualMedium, a.usualStyle) " +
            "FROM Artist a where a.archived = 0")
    List<ArtistDTO> retrieveArtistsDTO();
    @Query("SELECT new com.example.artGallery.dto.artist.ArtistDTO" +
            "(a.id, a.name, a.surname, a.address, a.phone, a.ssn,  a.usualType, a.usualMedium, a.usualStyle) FROM Artist a WHERE a.id = :artistId")
    Optional<ArtistDTO> retrieveArtistBasicInfoDTO(int artistId);

//    @Query("SELECT new com.example.artGallery.dto.artist.ActiveArtistDTO" +
//            "(st.artwork.artist.id, st.artwork.artist.name, st.artwork.artist.surname, st.artwork.artist.address, st.artwork.artist.phone, st.artwork.artist.ssn, st.artwork.artist.usualType, st.artwork.artist.usualMedium, st.artwork.artist.usualStyle, " +
//            "SUM(st.sellingPrice), SUM(sl.sellingPrice)) " +
//            "FROM Sale st  " +
//            "join Sale sl on st.id = sl.id " +
//            "where st.date >= :lastYearEnd " +
//            "and st.artwork.collector.name is null " +
//            "and sl.artwork.collector.name is null " +
//            "or (sl.date >= :lastYearBegin and sl.date <= :lastYearEnd) " +
//            "group by st.artwork.artist.id, st.artwork.artist.name, st.artwork.artist.surname, st.artwork.artist.address, st.artwork.artist.phone, st.artwork.artist.ssn, st.artwork.artist.usualType, st.artwork.artist.usualMedium, st.artwork.artist.usualStyle")
//    List<ActiveArtistDTO> retrieveActiveUsers(LocalDate lastYearBegin, LocalDate lastYearEnd);

//    @Query("SELECT new com.example.artGallery.dto.artist.ActiveArtistDTO" +
//            "(st.artwork.artist.id, st.artwork.artist.name, st.artwork.artist.surname, st.artwork.artist.address, st.artwork.artist.phone, st.artwork.artist.ssn, st.artwork.artist.usualType, st.artwork.artist.usualMedium, st.artwork.artist.usualStyle, " +
//            "SUM(st.sellingPrice), SUM(sl.sellingPrice)) " +
//            "FROM Sale st  " +
//            " JOIN Sale sl on st.id = sl.id " +
//            "where st.date >= :lastYearEnd " +
//            "and st.artwork.collector.name is null " +
//            "and sl.artwork.collector.name is null " +
//            "or (sl.date >= :lastYearBegin and sl.date <= :lastYearEnd) " +
//            "and st.artwork.artist.archived = 0" +
//            "group by st.artwork.artist.id, st.artwork.artist.name, st.artwork.artist.surname, st.artwork.artist.address, st.artwork.artist.phone, st.artwork.artist.ssn, st.artwork.artist.usualType, st.artwork.artist.usualMedium, st.artwork.artist.usualStyle")
//    List<ActiveArtistDTO> retrieveActiveUsers(LocalDate lastYearBegin, LocalDate lastYearEnd);

    @Query("SELECT new com.example.artGallery.dto.artist.ActiveArtistDTO" +
            "(a.artist.id, a.artist.name, a.artist.surname, a.artist.address, a.artist.phone, a.artist.ssn, a.artist.usualType, a.artist.usualMedium, a.artist.usualStyle, " +
            "SUM(ty.sellingPrice), SUM(ly.sellingPrice)) " +
            "FROM Artwork a  " +
            "left JOIN Sale ty on ty.artwork.id = a.id " +
            "and ty.date >= :lastYearEnd " +
            "left JOIN Sale ly on ly.artwork.id = a.id " +
            "and ly.date <= :lastYearEnd and ly.date >= :lastYearBegin " +
            " where a.collector is null and a.artist.archived = 0 " +
            " group by a.artist.id, a.artist.name, a.artist.surname, a.artist.address, a.artist.phone, a.artist.ssn, a.artist.usualType, a.artist.usualMedium, a.artist.usualStyle" +
            " having (SUM(ty.sellingPrice) is not null or SUM(ly.sellingPrice) is not null)")
    List<ActiveArtistDTO> retrieveActiveUsers(LocalDate lastYearBegin, LocalDate lastYearEnd);

    @Query("SELECT new com.example.artGallery.dto.artist.ArtistWorksDTO" +
            "(aw.title, aw.artType, aw.artMedium, aw.artStyle, aw.askingPrice, s.sellingPrice, s.date)" +
            "FROM Artist a " +
            "JOIN Artwork aw on aw.artist.id = a.id " +
            "LEFT JOIN Sale s on aw.id = s.artwork.artist.id " +
            "WHERE s.artwork.collector.name is null")
    List<ArtistWorksDTO> retrieveArtistWorks(int artistId);

    @Query("SELECT new com.example.artGallery.dto.artist.ArtistWorksDTO( a.title, a.artType, a.artMedium, a.artStyle, a.askingPrice, s.sellingPrice, s.date )" +
            " FROM Artwork a " +
            " left join Sale s on s.artwork.id = a.id" +
            " where a.artist.id = :artistId")
    List<ArtistWorksDTO> getArtworksFromArtist(int artistId);

    @Query("SELECT a.id from Artist a where a.ssn = :ssnValue")
    Optional<Integer> findBySSN(String ssnValue);

    @Query("SELECT new com.example.artGallery.dto.artist.ArtistBasicInfo(" +
            "a.id, a.ssn, a.name, a.surname) from Artist a where a.archived = 0")
    List<ArtistBasicInfo> getBasicInfo();
}
