package com.caNhan.E_conomy.Dto.ResponseDto;

public class ProductColorResponseDTO {
    private Long id;
    private String urlPhoto;
    private String titleVariant;
    private Long productId;

    public ProductColorResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getTitleVariant() {
        return titleVariant;
    }

    public void setTitleVariant(String titleVariant) {
        this.titleVariant = titleVariant;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
