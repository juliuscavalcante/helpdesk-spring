package com.helpdesk.springangularproject.domain;

import com.helpdesk.springangularproject.domain.enums.Profile;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Technician extends Person {

    @Serial
    private static final long serialVersionUID = 7160252738355265561L;

    @OneToMany(mappedBy = "technician")
    private List<Request> requests = new ArrayList<>();

    public Technician() {
        super();
        addProfile(Profile.TECHNICIAN);
    }

    public Technician(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.TECHNICIAN);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
