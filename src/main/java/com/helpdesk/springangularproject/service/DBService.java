package com.helpdesk.springangularproject.service;

import com.helpdesk.springangularproject.domain.Client;
import com.helpdesk.springangularproject.domain.Request;
import com.helpdesk.springangularproject.domain.Technician;
import com.helpdesk.springangularproject.domain.enums.Priority;
import com.helpdesk.springangularproject.domain.enums.Profile;
import com.helpdesk.springangularproject.domain.enums.Status;
import com.helpdesk.springangularproject.repository.ClientRepository;
import com.helpdesk.springangularproject.repository.RequestRepository;
import com.helpdesk.springangularproject.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RequestRepository requestRepository;

    public void instantiateDB() {
        Technician tec1 = new Technician(null, "Galandriel", "806.096.260-43", "galandriel@email.com", "123");
        tec1.addProfile(Profile.ADMIN);

        Client cli1 = new Client(null, "Elrond", "316.119.620-13", "elrond@email.com", "123");
        cli1.addProfile(Profile.CLIENT);

        Request req1 = new Request(null, Priority.MEDIUM, Status.PROGRESS, "Request 01", "Test request", tec1, cli1);

        technicianRepository.saveAll(Arrays.asList(tec1));

        clientRepository.saveAll(Arrays.asList(cli1));

        requestRepository.saveAll(Arrays.asList(req1));
    }
}
