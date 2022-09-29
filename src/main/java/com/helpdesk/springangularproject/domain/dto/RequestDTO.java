package com.helpdesk.springangularproject.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.springangularproject.domain.Request;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class RequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7663703502420151568L;

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;

    @NotNull(message = "priority is required")
    private Integer priority;

    @NotNull(message = "status is required")
    private Integer status;

    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "notes is required")
    private String notes;

    @NotNull(message = "technician is required")
    private Long technician;

    @NotNull(message = "customer is required")
    private Long customer;

    private String technicianName;

    private String customerName;

    public RequestDTO() {
        super();
    }

    public RequestDTO(Request request) {
        this.id = request.getId();
        this.openingDate = request.getOpeningDate();
        this.closingDate = request.getClosingDate();
        this.priority = request.getPriority().getCode();
        this.status = request.getStatus().getCode();
        this.title = request.getTitle();
        this.notes = request.getNotes();
        this.technician = request.getTechnician().getId();
        this.customer = request.getCustomer().getId();
        this.technicianName = request.getTechnician().getName();
        this.customerName = request.getCustomer().getName();
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Long getTechnician() {
        return technician;
    }

    public void setTechnician(Long technician) {
        this.technician = technician;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
