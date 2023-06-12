package com.example.artGallery.services;

import com.example.artGallery.dto.sale.SaleArtInfo;
import com.example.artGallery.dto.sale.SaleDTO;
import com.example.artGallery.dto.salesperson.SalespersonCreateDTO;
import com.example.artGallery.dto.salesperson.SalespersonDTO;
import com.example.artGallery.exc.LocalizedException;
import com.example.artGallery.model.Salesperson;
import com.example.artGallery.repositories.SaleRepository;
import com.example.artGallery.repositories.SalespersonRepository;
import com.example.artGallery.utils.ObjectMapperUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalespersonService {
    private final SalespersonRepository salespersonRepository;
    private final SaleRepository saleRepository;

    public SalespersonService(SalespersonRepository salespersonRepository, SaleRepository saleRepository) {
        this.salespersonRepository = salespersonRepository;
        this.saleRepository = saleRepository;
    }

    public int addSalespersonAndGetId(SalespersonCreateDTO salesperson) throws IllegalArgumentException {
        System.out.println("adding salesperson");
        System.out.println(salesperson);
        Salesperson newSalesperson = ObjectMapperUtils.map(salesperson, new Salesperson());
        newSalesperson.setArchived(0);
        salespersonRepository.save(newSalesperson);
        return newSalesperson.getId();
    }

    public List<SalespersonDTO> getAllSalespersons()  {
        return salespersonRepository.retrieveSalespersonDTO();
    }

    public SalespersonDTO getSalesperson(int SalespersonId) {
        Optional<SalespersonDTO> salesperson = salespersonRepository.retrieveSalespersonInfoDTO(SalespersonId);
        if(salesperson.isEmpty()) {
            throw new LocalizedException("SalespersonId");
        }
        return salesperson.get();
    }

    public Boolean checkSalespersonExists(String ssn) {
        Optional<Integer> artistId = salespersonRepository.findBySSN(ssn);
        return artistId.isPresent();
    }

    public void delete(int id) {
        Optional<Salesperson> salespersonOpt = salespersonRepository.findById(id);
        if (salespersonOpt.isEmpty()) {
            throw new LocalizedException("id " + id);
        }
        Salesperson salesperson = salespersonOpt.get();
        salesperson.setArchived(1);
        salespersonRepository.save(salesperson);
//        List<Integer> sales = saleRepository.findIdsBySalespersonId(id);
//        if(sales.size() > 0) {
//            throw new LocalizedException("You cannot delete this salesperson, because she/he has past sales that are stored in the database.");
//        }
//        salespersonRepository.deleteById(id);
    }

    public Boolean checkSalespersonSSN(int id, String ssnValue) {
        Optional<Integer> salespersonId = salespersonRepository.findBySSN(ssnValue);
        return salespersonId.isEmpty() || id== salespersonId.get().intValue() ;
    }

    public void update(Salesperson salesperson) {
        salesperson.setArchived(0);
        this.salespersonRepository.save(salesperson);
    }

    public List<Integer> salesAmt(int salespersonId, LocalDate begin, LocalDate end) {
        return this.salespersonRepository.salesAmtInPeriod(salespersonId, begin, end);
    }


    public List<SaleArtInfo> getSoldArt(int salespersonId, LocalDate begin, LocalDate end) {
        return this.salespersonRepository.getSoldArt(salespersonId, begin, end);
    }

//    public SalespersonPerformanceDTO getSalespersonPerformanceInPeriod(int spId, String beginPeriod, String endPeriod) {
//        Optional<SalespersonPerformanceDTO> performance = salespersonRepository.retrieveSalespersonPerformanceInPeriod(spId, LocalDate.parse(beginPeriod), LocalDate.parse(endPeriod));
//        if(performance.isEmpty()) {
//            throw new LocalizedException("SalespersonId");
//        }
//        return performance.get();
//    }
}
