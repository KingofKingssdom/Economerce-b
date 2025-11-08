package com.caNhan.E_conomy.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_variant")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storage;
    @Column(name = "price_origin")
    private double priceOrigin;
    @Column(name = "price_discount")
    private double priceDiscount;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public ProductVariant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public double getPriceOrigin() {
        return priceOrigin;
    }

    public void setPriceOrigin(double priceOrigin) {
        this.priceOrigin = priceOrigin;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
