package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productVariant")
public class ProductVariantController {
    private ProductVariantService productVariantService;
    @Autowired
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }
    @PostMapping("/create")
    private ResponseEntity<?> addProductVariant(@ModelAttribute ProductVariantDTO productVariantDTO){
        ProductVariantDTO productVariant = productVariantService.create(productVariantDTO);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Thêm phiên bản sản phẩm thành công",
                productVariant
        );
        return ResponseEntity.ok(responseData);
    }
}
