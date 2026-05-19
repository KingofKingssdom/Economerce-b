package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.RequestDto.ReqBindSpecificationProductsDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqProductSpecificationDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductSpecificationDto;
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
    @PostMapping
    private ResponseEntity<?> create (@ModelAttribute ReqProductSpecificationDto reqProductSpecificationDto){
        ResProductSpecificationDto resProductSpecificationDto = productSpecificationService
                .createProductSpecification(reqProductSpecificationDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data created successfully",
                resProductSpecificationDto
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    private ResponseEntity<?> getAll() {
        List<ResProductSpecificationDto> resProductSpecificationDtoList  =
                productSpecificationService.getAllProductSpecification();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductSpecificationDtoList
        );
        return  ResponseEntity.ok(responseData);
    }

    @PutMapping("/{productSpecificationId}")
    private ResponseEntity<?> update(@PathVariable Long productSpecificationId,
                                     @ModelAttribute ReqProductSpecificationDto reqProductSpecificationDto){
        ResProductSpecificationDto resProductSpecificationDto = productSpecificationService
                .updateProductSpecification(productSpecificationId, reqProductSpecificationDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data updated successfully",
                resProductSpecificationDto
        );
        return ResponseEntity.ok(responseData);
    }
    @DeleteMapping("/{productSpecificationId}")
    private ResponseEntity<?> delete (@PathVariable Long productSpecificationId){
        productSpecificationService.deleteProductSpecification(productSpecificationId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data updated successfully"
        );
        return ResponseEntity.ok(responseData);
    }
    @PostMapping("/bind-products")
    public ResponseEntity<?> bindProducts(
            @ModelAttribute ReqBindSpecificationProductsDto dto) {
        ResProductSpecificationDto result = productSpecificationService.createProductsToSpecification(dto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data created successfully",
                result
        );
        return ResponseEntity.ok(responseData);
    }


}
