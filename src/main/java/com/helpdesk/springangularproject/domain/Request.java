package com.helpdesk.springangularproject.domain;

import com.helpdesk.springangularproject.domain.enums.Priority;
import com.helpdesk.springangularproject.domain.enums.Status;

import java.time.LocalDate;
import java.util.Objects;

public class Request {

    private Integer id;
    private LocalDate openingDate = LocalDate.now();
    private LocalDate closingDate;
    private Priority prioridade;
    private Status status;
    private String title;
    private String notes;

    private Technician technician;
    private Client client;

    public Request() {
        super();
    }

    public Request(Integer id, Priority prioridade, Status status, String title, String notes, Technician technician, Client client) {
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.title = title;
        this.notes = notes;
        this.technician = technician;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Priority getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Priority prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
