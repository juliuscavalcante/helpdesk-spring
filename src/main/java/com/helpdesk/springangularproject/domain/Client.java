package com.helpdesk.springangularproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.springangularproject.domain.enums.Profile;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Person {

    @Serial
    private static final long serialVersionUID = 7577864164365000261L;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Request> requests = new ArrayList<>();

    public Client() {
        super();
        addProfile(Profile.CLIENT);
    }

    public Client(Long id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CLIENT);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
