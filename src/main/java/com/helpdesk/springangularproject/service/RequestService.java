package com.helpdesk.springangularproject.service;

import com.helpdesk.springangularproject.domain.Customer;
import com.helpdesk.springangularproject.domain.Request;
import com.helpdesk.springangularproject.domain.Technician;
import com.helpdesk.springangularproject.domain.dto.RequestDTO;
import com.helpdesk.springangularproject.domain.enums.Priority;
import com.helpdesk.springangularproject.domain.enums.Status;
import com.helpdesk.springangularproject.repository.RequestRepository;
import com.helpdesk.springangularproject.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private CustomerService customerService;

    public Request findById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        return requestOptional.orElseThrow(() -> new ObjectNotFoundException("Request id " + id + " not found"));
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request create(@Valid RequestDTO requestDTO) {
        return requestRepository.save(newRequest(requestDTO));
    }

    private Request newRequest(RequestDTO requestDTO) {
        Technician technician = technicianService.findById(requestDTO.getTechnician());
        Customer customer = customerService.findById(requestDTO.getCustomer());

        Request request = new Request();
        if (requestDTO.getId() != null) {
            request.setId(requestDTO.getId());
        }

        request.setTechnician(technician);
        request.setCustomer(customer);
        request.setPriority(Priority.toEnum(requestDTO.getPriority()));
        request.setStatus(Status.toEnum(requestDTO.getStatus()));
        request.setTitle(requestDTO.getNotes());
        request.setNotes(requestDTO.getNotes());
        return request;
    }
}
