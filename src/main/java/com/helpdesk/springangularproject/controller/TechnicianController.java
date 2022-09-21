package com.helpdesk.springangularproject.controller;

import com.helpdesk.springangularproject.domain.Technician;
import com.helpdesk.springangularproject.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Technician> findById(@PathVariable Long id) {
        Technician technician = this.technicianService.findById(id);
        return ResponseEntity.ok().body(technician);
    }
}
