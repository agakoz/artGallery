package com.example.artGallery.controllers;

import com.example.artGallery.dto.sale.*;
import com.example.artGallery.services.SalesService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SalesService salesService;

    @Autowired
    public SaleController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/all")
    @SneakyThrows
    public List<SaleExtendedInfoDTO> getAllSale() {
       return salesService.getAllSales();
    }

    @GetMapping("/thisWeek")
    @SneakyThrows
    public List<WeeklySales> getWeekSale() {
        return salesService.getThisWeekSales();
    }

//    @GetMapping("/{saleId}")
//    @SneakyThrows
//    @ResponseBody
//    public SaleDTO getSale(@PathVariable int saleId) {
//        return salesService.getSale(saleId);
//    }

    @PostMapping("/add")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> addSale(@RequestBody SaleCreateDTO sale) {
        System.out.println("adding sale controller");
        int spId = salesService.addSaleAndGetId(sale);
        return new ResponseEntity<>(spId, HttpStatus.CREATED);
    }

    @GetMapping("/lastWeek")
    @SneakyThrows
    @Transactional
    public  ResponseEntity<Object> getLastWeekSales() {
        List<SaleExtendedInfoDTO> sales = salesService.getSalesFromLastWeek();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{saleId}/receipt")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> getSaleReceipt(@PathVariable int saleId) {
        SaleReceiptDTO saleReceipt = salesService.getReceiptForSale(saleId);
        return new ResponseEntity<>(saleReceipt, HttpStatus.OK);
    }

    @GetMapping("/{saleId}/paymentStub")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> getSalePaymentStub(@PathVariable int saleId) {
        PaymentStubDTO paymentStub = salesService.getPaymentStub(saleId);
        return new ResponseEntity<>(paymentStub, HttpStatus.OK);
    }

    @GetMapping("/{spId}/{beginPeriod}/{endPeriod}")
    @SneakyThrows
    @ResponseBody
    public List<SaleArtInfo> getSalespersonPerformanceInfo(@PathVariable int spId, @PathVariable String beginPeriod, @PathVariable String endPeriod) {
        return  salesService.getSalesFromSalespersonInPeriod(spId, beginPeriod, endPeriod);
    }
}
