package com.example.artGallery.controllers;

import com.example.artGallery.dto.artist.ArtistWorksDTO;
import com.example.artGallery.dto.collector.CollectorCreateDTO;
import com.example.artGallery.dto.collector.CollectorDTO;
import com.example.artGallery.dto.collector.CollectorOwnerDTO;
import com.example.artGallery.dto.collector.OwnerWorksDTO;
import com.example.artGallery.enums.SsnDTO;
import com.example.artGallery.enums.SsnIdDTO;
import com.example.artGallery.model.Artist;
import com.example.artGallery.model.Collector;
import com.example.artGallery.services.CollectorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/collector")
public class CollectorController {
    private final CollectorService collectorService;
    @Autowired
    public CollectorController(CollectorService collectorService) {
        this.collectorService = collectorService;
    }

    @GetMapping("/all")
    @SneakyThrows
    public ResponseEntity<Object> getAllCollectors() {

        List<CollectorDTO> collectors = collectorService.getAllCollectors();
        return new ResponseEntity<>(collectors, HttpStatus.OK);
    }

    @PostMapping("/update")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> updateArtist(@RequestBody Collector collector) {
        collectorService.update(collector);
        return new ResponseEntity<>(true, HttpStatus.CREATED);

    }

    @PostMapping("/validateSSN")
    @SneakyThrows
    @Transactional
    public Boolean validateSSN(@RequestBody SsnIdDTO ssn) {
        return collectorService.checkSSN(ssn.getId(), ssn.getSsnValue());

    }

//    @PostMapping("/validateSSN")
//    @SneakyThrows
//    @Transactional
//    public Boolean validateArtistSSN(@RequestBody SsnIdDTO ssn) {
//        return collectorService.checkArtistSSN(ssn.getId(), ssn.getSsnValue());
//
//    }

    @PostMapping("/collectorExist")
    @SneakyThrows
    @Transactional
    public Boolean checkIfArtistExistsBySSN(@RequestBody SsnDTO ssn) {
        return collectorService.checkIfArtistWithSSNExists(ssn.getSsnValue());

    }

    @GetMapping("/{collectorId}")
    @SneakyThrows
    @ResponseBody
    public CollectorDTO getCollector(@PathVariable int collectorId) {
        return  collectorService.getCollector(collectorId);
    }

    @GetMapping("/collectorOwners")
    @SneakyThrows
    @ResponseBody
    public List<CollectorOwnerDTO> getCollectorOwners() {
        return  collectorService.getActiveCollectors();
    }

    @PostMapping("/add")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> addCollector(@RequestBody CollectorCreateDTO collector) {
        System.out.println("adding patient controller");
        int collectorId = collectorService.addCollectorAndGetId(collector);
        return new ResponseEntity<>(collectorId, HttpStatus.CREATED);
    }
    @GetMapping("/report/{id}")
    @SneakyThrows
    @ResponseBody
    public ResponseEntity<Object> getOwnerReport(@PathVariable int id) {
        List<OwnerWorksDTO> ownerReport = collectorService.getOwnerReport(id);
        return new ResponseEntity<>(ownerReport, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Object> deleteCollector(@PathVariable int id) {
        try {

            collectorService.delete(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    String.format(ex.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

    }
}
