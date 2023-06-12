package com.example.artGallery.services;

import com.example.artGallery.dto.sale.*;
import com.example.artGallery.exc.LocalizedException;
import com.example.artGallery.model.*;
import com.example.artGallery.repositories.*;
import com.example.artGallery.utils.ObjectMapperUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {
    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final ArtistRepository artistRepository;
    private final SalespersonRepository salespersonRepository;
    private final CollectorRepository collectorRepository;
    private final ArtworkRepository artworkRepository;

    public SalesService(SaleRepository saleRepository, CustomerRepository customerRepository, ArtistRepository artistRepository, SalespersonRepository salespersonRepository, CollectorRepository collectorRepository, ArtworkRepository artworkRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.artistRepository = artistRepository;
        this.salespersonRepository = salespersonRepository;
        this.collectorRepository = collectorRepository;
        this.artworkRepository = artworkRepository;
    }
//    @Autowired
//    public SaleService(SaleRepository saleRepository) {
//        this.saleRepository = saleRepository;
//    }

    public int addSaleAndGetId(SaleCreateDTO saleDTO) throws IllegalArgumentException {
        System.out.println("adding sale");
        System.out.println(saleDTO);
//        Sale sale = createSaleFromDTO(saleDTO);
        Optional<Customer> customer = customerRepository.findById(saleDTO.getCustomerId());
        Optional<Artwork> artwork = artworkRepository.findById(saleDTO.getArtworkId());
        Optional<Salesperson> salesperson = salespersonRepository.findById(saleDTO.getSalespersonId());
        if(customer.isEmpty() || artwork.isEmpty() || salesperson.isEmpty()) {
            throw new LocalizedException("sale error");
        }
        Sale newSale = ObjectMapperUtils.map(saleDTO, new Sale());
        newSale.setArtwork(artwork.get());
        newSale.setSalesperson(salesperson.get());
        newSale.setCustomer(customer.get());
        saleRepository.save(newSale);
        return newSale.getId();
    }

    private Sale createSaleFromDTO(SaleCreateDTO saleDTO) {
        Customer customer = customerRepository.findById(saleDTO.getCustomerId()).get();
        Artwork artwork = artworkRepository.findById(saleDTO.getArtworkId()).get();
        Salesperson salesperson = salespersonRepository.findById(saleDTO.getSalespersonId()).get();

        return new Sale(-1, artwork, customer, salesperson, saleDTO.getDate(), saleDTO.getSellingPrice());
    }

    public List<SaleExtendedInfoDTO> getAllSales()  {
        return saleRepository.getAllPastSales();
    }

    public List<WeeklySales> getThisWeekSales()  {
        LocalDate today = LocalDate.now();
        LocalDate monday = LocalDate.now().with(DayOfWeek.MONDAY);
        List<WeeklySales> sales = this.saleRepository.getInfoForWeekSales(monday, today);
        sales.forEach(salesperson -> salesperson.setSales(this.saleRepository.getSale(salesperson.getSalespersonId(), monday, today)) );
        return sales;
    }
//    public SaleDTO getSale(int SaleId) {
//        Optional<SaleDTO> sale = saleRepository.retrieveSaleInfoDTO(SaleId);
//        if(sale.isEmpty()) {
//            throw new LocalizedException("No sale with id: " + SaleId);
//        }
//        return sale.get();
//    }

    public List<SaleExtendedInfoDTO> getSalesFromLastWeek() {
        LocalDate lastWeekBegin = LocalDate.now().minusWeeks(1);
        return saleRepository.retrieveSaleInfoSinceGivenDate(lastWeekBegin);
    }

    public SaleReceiptDTO getReceiptForSale(int saleId) {
        Optional<SaleReceiptDTO> receipt = saleRepository.getReceiptInfo(saleId);
        if(receipt.isEmpty()) {
            throw new LocalizedException("Sale Id problem: " + saleId);
        }
        return receipt.get();
    }

    public PaymentStubDTO getPaymentStub(int saleId) {
        Optional<PaymentStubDTO> paymentStub = saleRepository.getPaymentStub(saleId);
        if(paymentStub.isEmpty()) {
            throw new LocalizedException("Sale Id problem: " + saleId);
        }
        return paymentStub.get();
    }

    public List<SaleArtInfo> getSalesFromSalespersonInPeriod(int spId, String beginPeriod, String endPeriod) {
        return saleRepository.getSalesFromSalespersonInPeriod(spId, beginPeriod, endPeriod);
    }


}
