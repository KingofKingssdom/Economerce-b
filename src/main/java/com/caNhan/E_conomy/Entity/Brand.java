package com.caNhan.E_conomy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Brands")
public class Brand {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "BrandName")
    private String  brandName;
    @Column(name = "imageBrand", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] imageBrand;

    // Mapping
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "Brand_Category",
            joinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    @JsonIgnore
    private List<Category> categories;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "brands")
    @JsonIgnore
    private List<Product> products;
    public Brand() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public byte[] getImageBrand() {
        return imageBrand;
    }

    public void setImageBrand(byte[] imageBrand) {
        this.imageBrand = imageBrand;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
