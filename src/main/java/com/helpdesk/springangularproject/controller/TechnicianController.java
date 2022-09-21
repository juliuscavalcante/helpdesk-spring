package com.helpdesk.springangularproject.controller;

import com.helpdesk.springangularproject.domain.Technician;
import com.helpdesk.springangularproject.domain.dto.TechnicianDTO;
import com.helpdesk.springangularproject.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        List<Technician> technicianList = technicianService.findAll();
        List<TechnicianDTO> technicianDTOList = technicianList.stream().
                map(technician -> new TechnicianDTO(technician)).collect(Collectors.toList());
        return ResponseEntity.ok().body(technicianDTOList);
    }



    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Long id) {
        Technician technician = this.technicianService.findById(id);
        return ResponseEntity.ok().body(new TechnicianDTO(technician));
    }
}
