package com.helpdesk.springangularproject.service;

import com.helpdesk.springangularproject.domain.Person;
import com.helpdesk.springangularproject.domain.Technician;
import com.helpdesk.springangularproject.domain.dto.TechnicianDTO;
import com.helpdesk.springangularproject.repository.PersonRepository;
import com.helpdesk.springangularproject.repository.TechnicianRepository;
import com.helpdesk.springangularproject.service.exception.DataIntegrityViolationException;
import com.helpdesk.springangularproject.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Technician findById(Long id) {
        Optional<Technician> technicianOptional = technicianRepository.findById(id);
        return technicianOptional.orElseThrow(() -> new ObjectNotFoundException("technician id " + id + " not found"));
    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician create(TechnicianDTO technicianDTO) {
        technicianDTO.setId(null);
        technicianDTO.setPassword(encoder.encode(technicianDTO.getPassword()));
        validateCpfAndEmail(technicianDTO);
        Technician technician = new Technician(technicianDTO);
        return technicianRepository.save(technician);
    }

    public Technician update(Long id, @Valid TechnicianDTO technicianDTO) {
        technicianDTO.setId(id);
        Technician oldTechnician = findById(id);

        if (!technicianDTO.getPassword().equals(oldTechnician.getPassword()))
            technicianDTO.setPassword(encoder.encode(technicianDTO.getPassword()));

        validateCpfAndEmail(technicianDTO);
        oldTechnician = new Technician(technicianDTO);
        return technicianRepository.save(oldTechnician);
    }

    public void delete(Long id) {
        Technician technician = findById(id);
        if(technician.getRequests().size() > 0) {
            throw new DataIntegrityViolationException("The Technician has one or more Service Orders, and it is not possible to delete him");
        }
        technicianRepository.deleteById(id);
    }

    private void validateCpfAndEmail(TechnicianDTO technicianDTO) {
        Optional<Person> technicianOptional = personRepository.findByCpf(technicianDTO.getCpf());
        if (technicianOptional.isPresent() && technicianOptional.get().getId() != technicianDTO.getId()) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }

        technicianOptional = personRepository.findByEmail(technicianDTO.getEmail());
        if (technicianOptional.isPresent() && technicianOptional.get().getId() != technicianDTO.getId()) {
            throw new DataIntegrityViolationException("This Email is already registered in our system");
        }
    }
}
