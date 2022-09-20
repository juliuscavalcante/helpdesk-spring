package com.helpdesk.springangularproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.springangularproject.domain.enums.Priority;
import com.helpdesk.springangularproject.domain.enums.Status;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 171216050681921927L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;

    private Priority prioridade;

    private Status status;

    private String title;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Request() {
        super();
    }

    public Request(Long id, Priority prioridade, Status status, String title, String notes, Technician technician, Client client) {
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.title = title;
        this.notes = notes;
        this.technician = technician;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
