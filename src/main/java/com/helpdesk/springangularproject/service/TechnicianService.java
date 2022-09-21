package com.helpdesk.springangularproject.service;

import com.helpdesk.springangularproject.domain.Technician;
import com.helpdesk.springangularproject.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    public Technician findById(Long id) {
        Optional<Technician> technicianOptional = technicianRepository.findById(id);
        return technicianOptional.orElse(null);
    }
}
