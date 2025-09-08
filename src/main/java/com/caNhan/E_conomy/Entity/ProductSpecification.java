package com.caNhan.E_conomy.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ProductSpecifications")
public class ProductSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SpecificationName")
    private String nameSpecification;
    @ManyToMany
    @JoinTable(
            name = "product_specification_mapping",
            joinColumns = @JoinColumn(name = "specification_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    @OneToMany(mappedBy = "productSpecification")
    private List<SpecificationDetail> specificationDetails;
    public ProductSpecification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSpecification() {
        return nameSpecification;
    }

    public void setNameSpecification(String nameSpecification) {
        this.nameSpecification = nameSpecification;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<SpecificationDetail> getSpecificationDetails() {
        return specificationDetails;
    }

    public void setSpecificationDetails(List<SpecificationDetail> specificationDetails) {
        this.specificationDetails = specificationDetails;
    }
}
