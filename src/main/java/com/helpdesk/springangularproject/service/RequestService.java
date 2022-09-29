package com.helpdesk.springangularproject.service;

import com.helpdesk.springangularproject.domain.Request;
import com.helpdesk.springangularproject.repository.RequestRepository;
import com.helpdesk.springangularproject.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request findById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        return requestOptional.orElseThrow(() -> new ObjectNotFoundException("Request id " + id + " not found"));

    }
}
