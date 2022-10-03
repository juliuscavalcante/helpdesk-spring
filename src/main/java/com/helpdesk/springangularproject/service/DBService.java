package com.helpdesk.springangularproject.service;

import com.helpdesk.springangularproject.domain.Customer;
import com.helpdesk.springangularproject.domain.Request;
import com.helpdesk.springangularproject.domain.Technician;
import com.helpdesk.springangularproject.domain.enums.Priority;
import com.helpdesk.springangularproject.domain.enums.Profile;
import com.helpdesk.springangularproject.domain.enums.Status;
import com.helpdesk.springangularproject.repository.CustomerRepository;
import com.helpdesk.springangularproject.repository.RequestRepository;
import com.helpdesk.springangularproject.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instantiateDB() {
        Technician tec1 = new Technician(null, "Galandriel", "806.096.260-43", "galandriel@email.com", encoder.encode("123"));
        Technician tec2 = new Technician(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
        Technician tec3 = new Technician(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
        Technician tec4 = new Technician(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
        Technician tec5 = new Technician(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));
        tec1.addProfile(Profile.ADMIN);

        Customer cli1 = new Customer(null, "Elrond", "316.119.620-13", "elrond@email.com", encoder.encode("123"));
        Customer cli2 = new Customer(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
        Customer cli3 = new Customer(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
        Customer cli4 = new Customer(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
        Customer cli5 = new Customer(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
        cli1.addProfile(Profile.CUSTOMER);

        Request req1 = new Request(null, Priority.MEDIUM, Status.PROGRESS, "Request 01", "Test request", tec1, cli1);
        Request req2 = new Request(null, Priority.HIGH, Status.OPEN, "Chamado 2", "Teste chamado 2", tec1, cli2);
        Request req3 = new Request(null, Priority.LOW, Status.CLOSED, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Request req4 = new Request(null, Priority.HIGH, Status.OPEN, "Chamado 4", "Teste chamado 4", tec3, cli3);
        Request req5 = new Request(null, Priority.MEDIUM, Status.PROGRESS, "Chamado 5", "Teste chamado 5", tec2, cli1);

        technicianRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));

        customerRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));

        requestRepository.saveAll(Arrays.asList(req1, req2, req3, req4, req5));
    }
}
