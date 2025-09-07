//package com.caNhan.E_conomy.Entity;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "ProductSpecifications")
//public class ProductSpecification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "SpecificationName")
//    private String specificationName;
//    @Column(name = "SpecificationValue")
//    private String specificationValue;
//    @OneToMany(mappedBy = "productSpecification")
//    private List<SpecificationDetail> specificationDetails;
//    // Các quan hệ mapping
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    public ProductSpecification() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getSpecificationName() {
//        return specificationName;
//    }
//
//    public void setSpecificationName(String specificationName) {
//        this.specificationName = specificationName;
//    }
//
//    public String getSpecificationValue() {
//        return specificationValue;
//    }
//
//    public void setSpecificationValue(String specificationValue) {
//        this.specificationValue = specificationValue;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//}
