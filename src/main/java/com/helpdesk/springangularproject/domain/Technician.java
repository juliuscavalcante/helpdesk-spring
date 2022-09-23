package com.helpdesk.springangularproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.springangularproject.domain.dto.TechnicianDTO;
import com.helpdesk.springangularproject.domain.enums.Profile;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Technician extends Person {

    @Serial
    private static final long serialVersionUID = 7160252738355265561L;

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Request> requests = new ArrayList<>();

    public Technician() {
        super();
        addProfile(Profile.TECHNICIAN);
    }

    public Technician(Long id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.TECHNICIAN);
    }

    public Technician(TechnicianDTO technicianDTO) {
        this.id = technicianDTO.getId();
        this.name = technicianDTO.getName();
        this.cpf = technicianDTO.getCpf();
        this.email = technicianDTO.getEmail();
        this.password = technicianDTO.getPassword();
        this.profiles = technicianDTO.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.creationDate = technicianDTO.getCreationDate();
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
