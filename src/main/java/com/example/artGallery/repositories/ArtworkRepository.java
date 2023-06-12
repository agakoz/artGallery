package com.example.artGallery.repositories;

import com.example.artGallery.dto.artist.ArtistWorksDTO;
import com.example.artGallery.dto.artwork.ArtworkDTO;
import com.example.artGallery.dto.artwork.ArtworkOnSaleDTO;
import com.example.artGallery.dto.customer.CustomerPreferenceDTO;
import com.example.artGallery.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtworkRepository extends JpaRepository<Artwork, Integer> {
    @Query("SELECT new com.example.artGallery.dto.artwork.ArtworkDTO" +
            "(a.id, a.title, a.artType, a.artMedium, a.artStyle,  a.askingPrice, a.artist.id, a.collector.id) FROM Artwork a")
    List<ArtworkDTO> retrieveArtworkDTO();

    @Query("SELECT new com.example.artGallery.dto.artwork.ArtworkDTO" +
            "(a.id, a.title, a.artType, a.artMedium, a.artStyle,  a.askingPrice, a.artist.id, a.collector.id) FROM Artwork a WHERE a.id = :artworkId")
    Optional<ArtworkDTO> retrieveArtworkInfoDTO(int artworkId);

    @Query("SELECT new com.example.artGallery.dto.artwork.ArtworkOnSaleDTO" +
            "(a.id, a.title, a.artist.id, a.artist.name, a.artist.surname, a.artType, a.artMedium, a.artStyle,  a.askingPrice, a.size, c.id, c.name, c.surname, f.id, f.name) " +
            "FROM Artwork a left join Collector c on a.collector.id = c.id left join UploadedFile f on f.id = a.file.id " +
            "where not exists " +
            "(select s.id from Sale s where s.artwork.id = a.id) ")
    List<ArtworkOnSaleDTO> retrieveArtworkOnSale();

    @Query("select a.id from Artwork a where a.artist.id=:artistId and a.title=:artworkTitle")
    Optional<Integer> getByArtistTitle(int artistId, String artworkTitle);

@Query("delete from Artwork a where a.artist.id = :artistId and a.id not in (select s.artwork.id from Sale s)")
@Modifying
    void deleteByArtistId(int artistId);


    @Query("delete from Artwork a where a.collector.id = :collectorId and a.id not in (select s.artwork.id from Sale s)")
    @Modifying
    void deleteByCollectorId(int collectorId);


    @Query("SELECT distinct new com.example.artGallery.dto.customer.CustomerPreferenceDTO(" +
            "c.id, c.name, c.surname, c.address, c.phone, c.prefType, c.prefMedium, c.prefStyle, ar.name, ar.surname)" +
            " from Customer c, Artwork a " +
            " left join Artist ar on ar.id = c.artist.id" +
            " where c.prefStyle = a.artStyle or c.prefType = a.artType or a.artMedium = c.prefMedium " +
            " and c.archived = 0 and a.id not in (select s.artwork.id from Sale s) " +
            " and a.id = :artworkId")
    List<CustomerPreferenceDTO> getCustomerInterestedInArtwork(int artworkId);
}
