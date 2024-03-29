package com.domain.demoapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SupplierData {

    private Long id;

    @NotEmpty(message = "Name is required!")
    private String name;

    @NotEmpty(message = "Address is required!")
    private String address;

    @NotEmpty(message = "Email is required!")
    @Email(message = "Email is not valid!")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
