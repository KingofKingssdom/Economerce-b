//package com.caNhan.E_conomy.Entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "specification_detail")
//public class SpecificationDetail {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "specification_label")
//    private String labelSpecification;
//    @Column(name = "specification_description_detail")
//    private String detailSpecification;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_specification_id")
//    private ProductSpecification productSpecification;
//    public SpecificationDetail() {
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
//    public String getLabelSpecification() {
//        return labelSpecification;
//    }
//
//    public void setLabelSpecification(String labelSpecification) {
//        this.labelSpecification = labelSpecification;
//    }
//
//    public String getDetailSpecification() {
//        return detailSpecification;
//    }
//
//    public void setDetailSpecification(String detailSpecification) {
//        this.detailSpecification = detailSpecification;
//    }
//}
