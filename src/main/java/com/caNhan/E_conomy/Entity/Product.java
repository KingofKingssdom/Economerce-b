package com.caNhan.E_conomy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ProductName")
    private String productName;
    @Column(name = "PricePrevious")
    private double pricePrevious;
    @Column(name = "PriceCurrent")
    private double priceCurrent;
    @Column(name = "DiscountPercent")
    private String discountPercent;
    @Column(name = "Quantity")
    private String quantity;
    @Column(name = "Status")
    private String status;
    @Column(name = "installment")
    private String installment;
    @Column(name = "description")
    private String description;
    @Column(name = "productImage", columnDefinition = "LONGBLOB")
    private byte [] productImage;
    // Các quan hệ mapping
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="product_brand",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Brand> brands;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnore
    private List<Category> categories;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPricePrevious() {
        return pricePrevious;
    }

    public void setPricePrevious(double pricePrevious) {
        this.pricePrevious = pricePrevious;
    }

    public double getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(double priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
