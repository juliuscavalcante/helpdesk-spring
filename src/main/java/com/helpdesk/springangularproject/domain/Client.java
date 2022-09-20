package com.helpdesk.springangularproject.domain;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {

    private List<Request> requests = new ArrayList<Request>();

    public Client() {
        super();
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
