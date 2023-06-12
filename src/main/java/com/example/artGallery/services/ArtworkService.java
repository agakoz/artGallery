package com.example.artGallery.services;

import com.example.artGallery.dto.artwork.ArtworkCreateDTO;
import com.example.artGallery.dto.artwork.ArtworkDTO;
import com.example.artGallery.dto.artwork.ArtworkOnSaleDTO;
import com.example.artGallery.dto.artwork.ArtworkUpdateDTO;
import com.example.artGallery.dto.customer.CustomerPreferenceDTO;
import com.example.artGallery.exc.LocalizedException;
import com.example.artGallery.model.Artist;
import com.example.artGallery.model.Artwork;
import com.example.artGallery.model.Collector;
import com.example.artGallery.model.UploadedFile;
import com.example.artGallery.repositories.ArtistRepository;
import com.example.artGallery.repositories.ArtworkRepository;
import com.example.artGallery.repositories.CollectorRepository;
import com.example.artGallery.repositories.FilesRepository;
import com.example.artGallery.utils.ObjectMapperUtils;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final ArtistRepository artistRepository;
    private final CollectorRepository collectorRepository;
    private final FilesRepository fileRepository;

    public ArtworkService(ArtworkRepository artworkRepository, ArtistRepository artistRepository, CollectorRepository collectorRepository, FilesRepository fileRepository) {
        this.artworkRepository = artworkRepository;
        this.artistRepository = artistRepository;
        this.collectorRepository = collectorRepository;
        this.fileRepository = fileRepository;
    }

    public int addArtworkAndGetId(ArtworkCreateDTO artwork) throws LocalizedException {
        System.out.println("adding artwork");
        System.out.println(artwork);
        Optional<Artist> artist = artistRepository.findById(artwork.getArtistId());
        if (artist.isEmpty()) {
            throw new LocalizedException("no artist id: " + artwork.getArtistId());
        }
        Artwork newArtwork = ObjectMapperUtils.map(artwork, new Artwork());
        newArtwork.setArtist(artist.get());
        if (artwork.getFileId() != null) {

            Optional<UploadedFile> file = fileRepository.findById(artwork.getFileId());
            if (file.isEmpty()) {
                throw new LocalizedException("no file id: " + artwork.getFileId());
            }
            newArtwork.setFile(file.get());

        }

        if (artwork.getCollectorId() != null) {
            collectorRepository.findById(artwork.getCollectorId());
            Optional<Collector> collector = collectorRepository.findById(artwork.getCollectorId());
            if (collector.isEmpty()) {
                throw new LocalizedException("no artist id: " + artwork.getArtistId());
            }
            newArtwork.setCollector(collector.get());
        }
        artworkRepository.save(newArtwork);
        return newArtwork.getId();
    }

    public List<ArtworkDTO> getAllArtwork() {
        List<ArtworkDTO> Artwork = artworkRepository.retrieveArtworkDTO();
        return Artwork;
    }

    public ArtworkDTO getArtwork(int ArtworkId) {
        Optional<ArtworkDTO> artwork = artworkRepository.retrieveArtworkInfoDTO(ArtworkId);
        if (artwork.isEmpty()) {
            throw new LocalizedException("Problem artwork:" + ArtworkId);
        }
        return artwork.get();
    }

    public List<ArtworkOnSaleDTO> getArtworksOnSale() {
        return artworkRepository.retrieveArtworkOnSale();
    }

    public Boolean checkArtistTitleExist(int artistId, String artworkTitle) {
        Optional<Integer> artworkId = artworkRepository.getByArtistTitle(artistId, artworkTitle);
        return artworkId.isPresent();
    }

    public Boolean delete(int id) {
        this.artworkRepository.deleteById(id);
        Optional<Artwork> art = this.artworkRepository.findById(id);
        return art.isEmpty();
    }

    public List<CustomerPreferenceDTO> getCustomerInterestedInArtwork(int artworkId) {
        return artworkRepository.getCustomerInterestedInArtwork(artworkId);
    }

//    public void update(Artwork artwork) {
//        this.artworkRepository.save(artwork);
//    }

    public @NonNull int update(ArtworkUpdateDTO artwork) {
        Artist artist = artistRepository.getById(artwork.getArtistId());
        Artwork oldArt = artworkRepository.getById(artwork.getId());
        Artwork newArtwork = ObjectMapperUtils.map(artwork, oldArt);
        newArtwork.setArtist(artist);
        if (artwork.getFileId() != null) {
            Optional<UploadedFile> file = fileRepository.findById(artwork.getFileId());
            if (file.isEmpty()) {
                throw new LocalizedException("no file id: " + artwork.getFileId());
            }
            newArtwork.setFile(file.get());
        }

        if (artwork.getCollectorId() != null) {
            collectorRepository.findById(artwork.getCollectorId());
            Optional<Collector> collector = collectorRepository.findById(artwork.getCollectorId());
            if (collector.isEmpty()) {
                throw new LocalizedException("no artist id: " + artwork.getArtistId());
            }
            newArtwork.setCollector(collector.get());
        }
        artworkRepository.save(newArtwork);
        return newArtwork.getId();
    }
}
