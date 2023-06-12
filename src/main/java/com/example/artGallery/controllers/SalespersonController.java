package com.example.artGallery.controllers;

import com.example.artGallery.dto.sale.SaleArtInfo;
import com.example.artGallery.dto.sale.SaleDTO;
import com.example.artGallery.dto.salesperson.SalespersonCreateDTO;
import com.example.artGallery.dto.salesperson.SalespersonDTO;
import com.example.artGallery.enums.SsnIdDTO;
import com.example.artGallery.model.Salesperson;
import com.example.artGallery.services.SalespersonService;
import lombok.SneakyThrows;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/salesperson")
public class SalespersonController {

    private SalespersonService salespersonService;

    @Autowired
    public SalespersonController(SalespersonService salespersonService) {
        this.salespersonService = salespersonService;
    }

    @GetMapping("/all")
    @SneakyThrows
    public ResponseEntity<Object> getAllSalesperson() {

        List<SalespersonDTO> salespersons = salespersonService.getAllSalespersons();
        return new ResponseEntity<>(salespersons, HttpStatus.OK);
    }

    @GetMapping("/exists/{ssn}")
    @SneakyThrows
    public Boolean checkSalespersonExistsBySSN(@PathVariable String ssn) {
        return salespersonService.checkSalespersonExists(ssn);
    }

    @GetMapping("/{salespersonId}")
    @SneakyThrows
    @ResponseBody
    public SalespersonDTO getSalesperson(@PathVariable int salespersonId) {
        return salespersonService.getSalesperson(salespersonId);
    }
    @GetMapping("/{salespersonId}/saleAmt/{begin}/{end}")
    @SneakyThrows
    @ResponseBody
    public List<Integer> getSalesAmt(@PathVariable int salespersonId, @PathVariable String begin, @PathVariable String end) {
        return salespersonService.salesAmt(salespersonId, LocalDate.parse(begin), LocalDate.parse(end));
    }

    @GetMapping("/{salespersonId}/soldArt/{begin}/{end}")
    @SneakyThrows
    @ResponseBody
    public List<SaleArtInfo> getSoldArt(@PathVariable int salespersonId, @PathVariable String begin, @PathVariable String end) {
        return salespersonService.getSoldArt(salespersonId, LocalDate.parse(begin), LocalDate.parse(end));
    }

    @PostMapping("/add")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> addSalesperson(@RequestBody SalespersonCreateDTO salesperson) {
        System.out.println("adding salesperson controller");
        int spId = salespersonService.addSalespersonAndGetId(salesperson);
        return new ResponseEntity<>(spId, HttpStatus.CREATED);
    }
    @GetMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Object> deleteArtist(@PathVariable int id) {
        try {

            salespersonService.delete(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    String.format(ex.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/update")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> update(@RequestBody Salesperson salesperson) {
        salespersonService.update(salesperson);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @PostMapping("/validateSSN")
    @SneakyThrows
    @Transactional
    public Boolean validateSalespersonSSN(@RequestBody SsnIdDTO ssn) {
        return salespersonService.checkSalespersonSSN(ssn.getId(), ssn.getSsnValue());

    }
//    @GetMapping("/{spId}/performance/{beginPeriod}/{endPeriod}")
//    @SneakyThrows
//    @ResponseBody
//    public SalespersonPerformanceDTO getSalespersonPerformanceInfo(@PathVariable int spId, @PathVariable String beginPeriod, @PathVariable String endPeriod) {
//        return  salespersonService.getSalespersonPerformanceInPeriod(spId, beginPeriod, endPeriod);
//    }


}
