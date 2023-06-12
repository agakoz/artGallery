package com.example.artGallery.controllers;

import com.example.artGallery.dto.artist.*;
import com.example.artGallery.enums.SsnDTO;
import com.example.artGallery.enums.SsnIdDTO;
import com.example.artGallery.exc.LocalizedException;
import com.example.artGallery.model.Artist;
import com.example.artGallery.repositories.ArtistRepository;
import com.example.artGallery.services.ArtistService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistController(ArtistService artistService, ArtistRepository artistRepository) {
        this.artistService = artistService;
        this.artistRepository = artistRepository;
    }

    @GetMapping("/all")
    @SneakyThrows
    public List<ArtistDTO> getAllArtists() {
//        System.out.println("HERE");
        List<ArtistDTO> artists = artistService.getAllArtists();
        return artists;
//        return artistRepository.findAll();
    }

    @GetMapping("/{artistId}")
    @SneakyThrows
    @ResponseBody
    public ArtistDTO getArtist(@PathVariable int artistId) {
        return artistService.getArtist(artistId);
    }

    @GetMapping("/{artistId}/artworks")
    @SneakyThrows
    @ResponseBody
    public List<ArtistWorksDTO> getArtworks(@PathVariable int artistId) {
        return artistService.getArtworksFromArtist(artistId);
    }

    @GetMapping("/activeArtists")
    @SneakyThrows
    @ResponseBody
    public List<ActiveArtistDTO> getActiveArtists() {
        return artistService.getActiveArtists();
    }

    @GetMapping("/basicInfo")
    @SneakyThrows
    @ResponseBody
    public List<ArtistBasicInfo> getArtistsBasicInfo() {
        return artistService.getArtistsBasicInfo();
    }

    @PostMapping("/add")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> addArtist(@RequestBody ArtistCreateDTO artist) {
        System.out.println("adding patient controller");
        int artistId = artistService.addArtistAndGetId(artist);
        return new ResponseEntity<>(artistId, HttpStatus.CREATED);

    }

    @PostMapping("/update")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> updateArtist(@RequestBody Artist artist) {
       artistService.updateArtist(artist);
        return new ResponseEntity<>(true, HttpStatus.CREATED);

    }

    @PostMapping("/artistExist")
    @SneakyThrows
    @Transactional
    public Boolean checkIfArtistExistsBySSN(@RequestBody SsnDTO ssn) {
        return artistService.checkIfArtistWithSSNExists(ssn.getSsnValue());

    }

    @PostMapping("/validateSSN")
    @SneakyThrows
    @Transactional
    public Boolean validateArtistSSN(@RequestBody SsnIdDTO ssn) {
        return artistService.checkArtistSSN(ssn.getId(), ssn.getSsnValue());

    }

//    @GetMapping("/report/{artistId}")
//    @SneakyThrows
//    @ResponseBody
//    public ResponseEntity<Object> getArtistReport(@PathVariable int artistId) {
//        List<ArtistWorksDTO> artistReport = artistService.getArtistReport(artistId);
//        return new ResponseEntity<>(artistReport, HttpStatus.OK);
//    }

    @GetMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Object> deleteArtist(@PathVariable int id) {
        try {

            artistService.delete(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    String.format(ex.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

    }

}
