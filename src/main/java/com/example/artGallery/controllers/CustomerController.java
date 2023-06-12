package com.example.artGallery.controllers;

import com.example.artGallery.dto.NameAddressDTO;
import com.example.artGallery.dto.customer.CustomerCreateDTO;
import com.example.artGallery.dto.customer.CustomerDTO;
import com.example.artGallery.dto.customer.CustomerUpdateDTO;
import com.example.artGallery.model.Customer;
import com.example.artGallery.services.CustomerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    @SneakyThrows
    public ResponseEntity<Object> getAllCustomers() {

        List<CustomerDTO> customers = customerService.getAllCustomers();
//        customers.sort((CustomerDTO c1, CustomerDTO c2) -> {
//            c1.getName().compareTo(c2.getName())
//        } );
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/basicInfo")
    @SneakyThrows
    public List<NameAddressDTO>  getCustomersBasicInfo() {
        return customerService.getCustomerBasicInfo();
    }

    @PostMapping("/customerExist")
    @SneakyThrows
    @Transactional
    public Boolean checkIfArtistExistsBySSN(@RequestBody NameAddressDTO nameAddressDTO) {
        return customerService.checkIfArtistWithAddressExists(nameAddressDTO);

    }

//    @GetMapping("/{customerId}")
//    @SneakyThrows
//    @ResponseBody
//    public CustomerDTO getCustomer(@PathVariable int customerId) {
//        return customerService.getCustomer(customerId);
//    }

    @PostMapping("/add")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerCreateDTO customer) {
        System.out.println("adding patient controller");
        int customerId = customerService.addCustomerAndGetId(customer);
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @PostMapping("/update")
    @SneakyThrows
    @Transactional
    public ResponseEntity<Object> update(@RequestBody CustomerUpdateDTO customer) {
        customerService.update(customer);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }
}
