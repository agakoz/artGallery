package com.example.artGallery.controllers;

import com.example.artGallery.dto.ArtistTitleDTO;
import com.example.artGallery.dto.artwork.ArtworkOnSaleDTO;
import com.example.artGallery.dto.artwork.ArtworkCreateDTO;
import com.example.artGallery.dto.artwork.ArtworkDTO;
import com.example.artGallery.dto.artwork.ArtworkUpdateDTO;
import com.example.artGallery.dto.customer.CustomerPreferenceDTO;
import com.example.artGallery.model.Artwork;
import com.example.artGallery.model.Collector;
import com.example.artGallery.services.ArtworkService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/artwork")
public class ArtworkController {

    private ArtworkService artworkService;

    @Autowired
    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping("/all")
    @SneakyThrows
    public ResponseEntity<Object> getAllArtwork() {

        List<ArtworkDTO> artwork = artworkService.getAllArtwork();
        return new ResponseEntity<>(artwork, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    @SneakyThrows
    @Transactional
    public Boolean deleteArtwork(@PathVariable int id) {
        return artworkService.delete(id);

    }


    @GetMapping("/{artworkId}")
    @SneakyThrows
    @ResponseBody
    public ArtworkDTO getArtwork(@PathVariable int artworkId) {
        return artworkService.getArtwork(artworkId);
    }


    @GetMapping("/{artworkId}/interestedCustomers")
    @SneakyThrows
    @ResponseBody
    public List<CustomerPreferenceDTO> getCustomerInterestedInArtwork(@PathVariable int artworkId) {
        return artworkService.getCustomerInterestedInArtwork(artworkId);
    }


    @PostMapping(value = "/add" )
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> addArtwork(@RequestBody ArtworkCreateDTO artwork) {
        System.out.println("adding artwork controller");
        int spId = artworkService.addArtworkAndGetId(artwork);
        return new ResponseEntity<>(spId, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> updateArtist(@RequestBody ArtworkUpdateDTO artwork) {
        artworkService.update(artwork);
        return new ResponseEntity<>(true, HttpStatus.CREATED);

    }

    @PostMapping("/artistExist")
    @SneakyThrows
    @Transactional
    public Boolean checkArtistTitleExist(@RequestBody ArtistTitleDTO artistTitleDTO) {
        System.out.println("adding artwork controller");
        return artworkService.checkArtistTitleExist(artistTitleDTO.getArtistId(), artistTitleDTO.getArtworkTitle());
    }

    @GetMapping("/onSale")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> getArtworksOnSale() {
        List<ArtworkOnSaleDTO> artwork = artworkService.getArtworksOnSale();
        return new ResponseEntity<>(artwork, HttpStatus.OK);
    }


}

