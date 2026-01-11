package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ProductSpecificationDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductSpecificationResponseDTO;
import com.caNhan.E_conomy.Entity.ProductSpecification;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productSpecification")
public class ProductSpecificationController {
    private ProductSpecificationService productSpecificationService;
    @Autowired
    public ProductSpecificationController(ProductSpecificationService productSpecificationService) {
        this.productSpecificationService = productSpecificationService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> addProductSpecification (@ModelAttribute ProductSpecificationDTO productSpecificationDTO){
        ProductSpecificationResponseDTO responseDTO = productSpecificationService.create(productSpecificationDTO);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Tạo thông số sản phẩm  thành công",
                responseDTO
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/search")
    private ResponseEntity<?> getProductSpecificationByProductId(@RequestParam(name = "productId") Long productId) {
        List<ProductSpecificationResponseDTO> specificationResponseDTOS  = productSpecificationService.findByProductId(productId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy thông số theo id sản phẩm thành công",
                specificationResponseDTOS
        );
        return  ResponseEntity.ok(responseData);
    }

}
