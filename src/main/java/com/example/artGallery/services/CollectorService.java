package com.example.artGallery.services;

import com.example.artGallery.dto.collector.CollectorCreateDTO;
import com.example.artGallery.dto.collector.CollectorDTO;
import com.example.artGallery.dto.collector.CollectorOwnerDTO;
import com.example.artGallery.dto.collector.OwnerWorksDTO;
import com.example.artGallery.exc.LocalizedException;
import com.example.artGallery.model.Artist;
import com.example.artGallery.model.Collector;
import com.example.artGallery.repositories.ArtworkRepository;
import com.example.artGallery.repositories.CollectorRepository;
import com.example.artGallery.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CollectorService {
    private final CollectorRepository collectorRepository;
    private final ArtworkRepository artworkRepository;

    public CollectorService(CollectorRepository collectorRepository, ArtworkRepository artworkRepository) {
        this.collectorRepository = collectorRepository;
        this.artworkRepository = artworkRepository;
    }

    public int addCollectorAndGetId(CollectorCreateDTO collector) throws IllegalArgumentException {
        System.out.println("adding collector");
        System.out.println(collector);
        Collector newCollector = ObjectMapperUtils.map(collector, new Collector());

        collectorRepository.save(newCollector);
        return newCollector.getId();
    }

    public List<CollectorDTO> getAllCollectors()  {
        return collectorRepository.retrieveCollectorsDTO();
    }

    public CollectorDTO getCollector(int CollectorId) {
        Optional<CollectorDTO> collector = collectorRepository.retrieveCollectorBasicInfoDTO(CollectorId);
        if(collector.isEmpty()) {
            throw new LocalizedException("CollectorId");
        }
        return collector.get();
    }

    public List<CollectorOwnerDTO> getActiveCollectors() {
        LocalDate beginLastYear = LocalDate.now().withDayOfMonth(1).withMonth(1).minus(1, ChronoUnit.YEARS);
        LocalDate endLastYear = LocalDate.now().withDayOfMonth(30).withMonth(12).minus(1, ChronoUnit.YEARS);

        return collectorRepository.retrieveCollectorOwner(beginLastYear, endLastYear);
    }

    public List<OwnerWorksDTO> getOwnerReport(int collectorId) {
        return collectorRepository.retrieveCollectorsWorks(collectorId);

    }

    public Boolean checkIfArtistWithSSNExists(String ssnValue) {
        Optional<Integer> collectorId = collectorRepository.findBySSN(ssnValue);
        return collectorId.isPresent();
    }

    public void update(Collector collector) {
        collector.setArchived(0);
        this.collectorRepository.save(collector);
    }

    public Boolean checkSSN(int id, String ssnValue) {
        Optional<Integer> collectorId = collectorRepository.findBySSN(ssnValue);
        return collectorId.isEmpty() || id== collectorId.get().intValue() ;
    }

    public void delete(int id) {
//        List<Integer> sales = saleRepository.findIdsByArtistId(artistId);
//        if(sales.size() > 0) {
//            throw new LocalizedException("You cannot delete this artist, because she/he has past sales that are stored in the database.");
//        }
        Optional<Collector> collectorOpt = collectorRepository.findById(id);
        if(collectorOpt.isEmpty()) {
            throw new LocalizedException("problem");
        }
        Collector collector = collectorOpt.get();
        collector.setArchived(1);
        artworkRepository.deleteByArtistId(id) ;
        collectorRepository.save(collector);
    }
}
