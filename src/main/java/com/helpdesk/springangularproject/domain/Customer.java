package com.helpdesk.springangularproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.springangularproject.domain.dto.CustomerDTO;
import com.helpdesk.springangularproject.domain.enums.Profile;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Customer extends Person {

    @Serial
    private static final long serialVersionUID = 7577864164365000261L;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Request> requests = new ArrayList<>();

    public Customer() {
        super();
        addProfile(Profile.CUSTOMER);
    }

    public Customer(Long id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CUSTOMER);
    }

    public Customer(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.cpf = customerDTO.getCpf();
        this.email = customerDTO.getEmail();
        this.password = customerDTO.getPassword();
        this.profiles = customerDTO.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.creationDate = customerDTO.getCreationDate();
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
