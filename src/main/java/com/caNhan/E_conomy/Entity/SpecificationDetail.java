package com.caNhan.E_conomy.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "specification_detail")
public class SpecificationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "specification_label")
    private String labelSpecification;
    @Column(name = "specification_description_detail")
    private String valueSpecification;
    @ManyToOne
    @JoinColumn(name = "product_specification_id")
    private ProductSpecification productSpecification;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public SpecificationDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelSpecification() {
        return labelSpecification;
    }

    public void setLabelSpecification(String labelSpecification) {
        this.labelSpecification = labelSpecification;
    }

    public String getValueSpecification() {
        return valueSpecification;
    }

    public void setValueSpecification(String valueSpecification) {
        this.valueSpecification = valueSpecification;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
