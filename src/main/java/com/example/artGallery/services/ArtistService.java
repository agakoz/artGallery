package com.example.artGallery.services;

import com.example.artGallery.dto.artist.*;
import com.example.artGallery.exc.LocalizedException;
import com.example.artGallery.model.Artist;
import com.example.artGallery.repositories.ArtistRepository;
import com.example.artGallery.repositories.ArtworkRepository;
import com.example.artGallery.repositories.SaleRepository;
import com.example.artGallery.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final SaleRepository saleRepository;
    private final ArtworkRepository artworkRepository;

    public ArtistService(ArtistRepository artistRepository, SaleRepository saleRepository, ArtworkRepository artworkRepository) {
        this.artistRepository = artistRepository;
        this.saleRepository = saleRepository;
        this.artworkRepository = artworkRepository;
    }
//    @Autowired
//    public ArtistService(ArtistRepository artistRepository) {
//        this.artistRepository = artistRepository;
//    }

    public int addArtistAndGetId(ArtistCreateDTO artist) throws IllegalArgumentException {
        System.out.println("adding artist");
        System.out.println(artist);
        Artist newArtist = ObjectMapperUtils.map(artist, new Artist());

        artistRepository.save(newArtist);
        newArtist.setArchived(0);
        return newArtist.getId();
    }

    public List<ArtistDTO> getAllArtists() {
        List<ArtistDTO> Artists = artistRepository.retrieveArtistsDTO();
        return Artists;
    }

    public ArtistDTO getArtist(int ArtistId) {
        Optional<ArtistDTO> artist = artistRepository.retrieveArtistBasicInfoDTO(ArtistId);
//        if(artist.isEmpty()) {
//            throw new ArtistWithIdNotExistsException(ArtistId);
//        }
        return artist.get();
    }

    public List<ActiveArtistDTO> getActiveArtists() {
        LocalDate lastYearBegin = LocalDate.now().withDayOfMonth(1).withMonth(1).minus(1, ChronoUnit.YEARS);
        LocalDate lastYearEnd = LocalDate.now().withDayOfMonth(30).withMonth(12).minus(1, ChronoUnit.YEARS);

        return artistRepository.retrieveActiveUsers(lastYearBegin, lastYearEnd);
    }

    public List<ArtistWorksDTO> getArtistReport(int artistId) {
        return artistRepository.retrieveArtistWorks(artistId);
    }


    public boolean checkIfArtistWithSSNExists(String ssnValue) {
        Optional<Integer> artistId = artistRepository.findBySSN(ssnValue);
        return artistId.isPresent();
    }

    public List<ArtistBasicInfo> getArtistsBasicInfo() {
        return artistRepository.getBasicInfo();
    }

    public void delete(int artistId) {
//        List<Integer> sales = saleRepository.findIdsByArtistId(artistId);
//        if(sales.size() > 0) {
//            throw new LocalizedException("You cannot delete this artist, because she/he has past sales that are stored in the database.");
//        }
        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        if(artistOpt.isEmpty()) {
            throw new LocalizedException("problem");
        }
        Artist artist = artistOpt.get();
        artist.setArchived(1);
        artworkRepository.deleteByArtistId(artistId) ;
        artistRepository.save(artist);
    }

    public void updateArtist(Artist artist) {
        artist.setArchived(0);
//        Artist artist = ObjectMapperUtils.map(artistDTO, new Artist());
        this.artistRepository.save(artist);
    }

    public Boolean checkArtistSSN(int id, String ssnValue) {
        Optional<Integer> artistId = artistRepository.findBySSN(ssnValue);
        return artistId.isEmpty() || id== artistId.get().intValue() ;
    }

    public List<ArtistWorksDTO> getArtworksFromArtist(int artistId) {
        return this.artistRepository.getArtworksFromArtist(artistId);
    }
}
