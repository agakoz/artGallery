package com.example.artGallery.services;

import com.example.artGallery.dto.NameAddressDTO;
import com.example.artGallery.dto.customer.CustomerCreateDTO;
import com.example.artGallery.dto.customer.CustomerDTO;
import com.example.artGallery.dto.customer.CustomerUpdateDTO;
import com.example.artGallery.exc.LocalizedException;
import com.example.artGallery.model.Artist;
import com.example.artGallery.model.Customer;
import com.example.artGallery.repositories.ArtistRepository;
import com.example.artGallery.repositories.CustomerRepository;
import com.example.artGallery.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ArtistRepository artistRepository;

    public CustomerService(CustomerRepository customerRepository, ArtistRepository artistRepository) {
        this.customerRepository = customerRepository;
        this.artistRepository = artistRepository;
    }

    public int addCustomerAndGetId(CustomerCreateDTO customer) throws IllegalArgumentException {
        System.out.println("adding customer");
        System.out.println(customer);
        Customer newCustomer = ObjectMapperUtils.map(customer, new Customer());
        Artist artist = artistRepository.getById(customer.getPrefArtist());
        newCustomer.setArtist(artist);
        customerRepository.save(newCustomer);
        return newCustomer.getId();
    }

    public List<CustomerDTO> getAllCustomers() {
        LocalDate lastYearBegin = LocalDate.now().withDayOfMonth(1).withMonth(1).minus(1, ChronoUnit.YEARS);
        LocalDate lastYearEnd = LocalDate.now().withDayOfMonth(30).withMonth(12).minus(1, ChronoUnit.YEARS);
        List<CustomerDTO> customers = customerRepository.retrieveCustomerDTO(lastYearBegin, lastYearEnd);
        return customers;
    }

//    public CustomerDTO getCustomer(int customerId) {
//        Optional<CustomerDTO> customer = customerRepository.retrieveCustomerInfoDTO(customerId);
//        return customer.get();
//    }

    public Boolean checkIfArtistWithAddressExists(NameAddressDTO nameAddressDTO) {
        Optional<Integer> customerId = customerRepository.findByNameAddress(nameAddressDTO.getName(), nameAddressDTO.getSurname(), nameAddressDTO.getAddress());
        return (customerId.isPresent() && (customerId.isPresent() && ((nameAddressDTO.getId() == 0) || nameAddressDTO.getId() != customerId.get().intValue())));
    }

    public void delete(Integer id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new LocalizedException("problem");
        }
        Customer customer = customerOpt.get();
        customer.setArchived(1);
        customerRepository.save(customer);
    }

    public List<NameAddressDTO> getCustomerBasicInfo() {
        return customerRepository.retrieveBasicInfo();

    }

    public void update(CustomerUpdateDTO customer) {

        Customer newCustomer = ObjectMapperUtils.map(customer, customerRepository.getById(customer.getId()));
        Artist artist = artistRepository.getById(customer.getPrefArtist());
        newCustomer.setArtist(artist);
        this.customerRepository.save(newCustomer);
    }
}
