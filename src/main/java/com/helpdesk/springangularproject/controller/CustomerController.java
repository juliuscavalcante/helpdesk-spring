package com.helpdesk.springangularproject.controller;

import com.helpdesk.springangularproject.domain.Customer;
import com.helpdesk.springangularproject.domain.dto.CustomerDTO;
import com.helpdesk.springangularproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<Customer> costumerList = customerService.findAll();
        List<CustomerDTO> customerDTOList = costumerList.stream().
                map(costumer -> new CustomerDTO(costumer)).collect(Collectors.toList());
        return ResponseEntity.ok().body(customerDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        Customer customer = this.customerService.findById(id);
        return ResponseEntity.ok().body(new CustomerDTO(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO customerDTO) {
      Customer customer = customerService.create(customerDTO);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}").buildAndExpand(customer.getId()).toUri();
      return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> update(@Valid @PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.update(id, customerDTO);
        return ResponseEntity.ok().body(new CustomerDTO(customer));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
