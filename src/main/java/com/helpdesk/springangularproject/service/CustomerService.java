package com.helpdesk.springangularproject.service;

import com.helpdesk.springangularproject.domain.Customer;
import com.helpdesk.springangularproject.domain.Person;
import com.helpdesk.springangularproject.domain.dto.CustomerDTO;
import com.helpdesk.springangularproject.repository.CustomerRepository;
import com.helpdesk.springangularproject.repository.PersonRepository;
import com.helpdesk.springangularproject.service.exception.DataIntegrityViolationException;
import com.helpdesk.springangularproject.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Customer findById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElseThrow(() -> new ObjectNotFoundException("Customer id " + id + " not found"));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer create(CustomerDTO customerDTO) {
        customerDTO.setId(null);
        customerDTO.setPassword(encoder.encode(customerDTO.getPassword()));
        validateCpfAndEmail(customerDTO);
        Customer customer = new Customer(customerDTO);
        return customerRepository.save(customer);
    }

    public Customer update(Long id, @Valid CustomerDTO customerDTO) {
        customerDTO.setId(id);
        Customer oldCustomer = findById(id);

        if (!customerDTO.getPassword().equals(oldCustomer.getPassword()))
            customerDTO.setPassword(encoder.encode(customerDTO.getPassword()));

        validateCpfAndEmail(customerDTO);
        oldCustomer = new Customer(customerDTO);
        return personRepository.save(oldCustomer);
    }

    public void delete(Long id) {
        Customer customer = findById(id);
        if(customer.getRequests().size() > 0) {
            throw new DataIntegrityViolationException("The Customer has ....... and it is not possible to delete him");
        }
        customerRepository.deleteById(id);
    }

    private void validateCpfAndEmail(CustomerDTO customerDTO) {
        Optional<Person> customerOptional = personRepository.findByCpf(customerDTO.getCpf());
        if (customerOptional.isPresent() && customerOptional.get().getId() != customerDTO.getId()) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }

        customerOptional = personRepository.findByEmail(customerDTO.getEmail());
        if (customerOptional.isPresent() && customerOptional.get().getId() != customerDTO.getId()) {
            throw new DataIntegrityViolationException("This Email is already registered in our system");
        }
    }
}
