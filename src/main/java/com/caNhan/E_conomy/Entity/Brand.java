package com.caNhan.E_conomy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Brands")
public class Brand {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_name")
    private String  brandName;
    @Column(name = "url_image_brand")
    private String urlImageBrand;

    // Mapping
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "Brand_Category",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    public Brand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getUrlImageBrand() {
        return urlImageBrand;
    }

    public void setUrlImageBrand(String urlImageBrand) {
        this.urlImageBrand = urlImageBrand;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
