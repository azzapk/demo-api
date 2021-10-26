package com.domain.demoapi.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required!")
    @Column(name = "product_name", length = 100)
    private String name;

    @NotEmpty(message = "Description is required!")
    @Column(name = "product_description", length = 500)
    private String description;

    private double price;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "tbl_product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    @JsonManagedReference
    private Set<Supplier> suppliers;

}
