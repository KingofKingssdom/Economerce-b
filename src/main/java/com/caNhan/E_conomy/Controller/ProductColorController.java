package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ProductColorDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductColorResponseDTO;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productColor")
public class ProductColorController {
    private ProductColorService productColorService;
    @Autowired
    public ProductColorController(ProductColorService productColorService) {
        this.productColorService = productColorService;
    }
    @PostMapping("/create")
    private ResponseEntity<?> addProductColor(@ModelAttribute ProductColorDTO productColorDTO) {
        ProductColorResponseDTO productColor = productColorService.create(productColorDTO);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Tạo phiên bản màu sắc sản phẩm thành công",
                productColor
        );
        return  ResponseEntity.ok(responseData);
    }
}
