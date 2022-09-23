package com.helpdesk.springangularproject.service;

import com.helpdesk.springangularproject.domain.Technician;
import com.helpdesk.springangularproject.domain.dto.TechnicianDTO;
import com.helpdesk.springangularproject.repository.TechnicianRepository;
import com.helpdesk.springangularproject.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    public Technician findById(Long id) {
        Optional<Technician> technicianOptional = technicianRepository.findById(id);
        return technicianOptional.orElseThrow(() -> new ObjectNotFoundException("technician id " + id + " not found"));
    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician create(TechnicianDTO technicianDTO) {
        technicianDTO.setId(null);
        Technician technician = new Technician(technicianDTO);
        return technicianRepository.save(technician);
    }
}
