package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.RequestDto.ReqProductVariantDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductVariantDto;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productVariant")
public class ProductVariantController {
    private ProductVariantService productVariantService;
    @Autowired
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }
    @PostMapping
    private ResponseEntity<?> create(@ModelAttribute ReqProductVariantDto reqProductVariantDto){
        ResProductVariantDto resProductVariantDto = productVariantService.createProductVariant(reqProductVariantDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data created successfully",
                resProductVariantDto
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/{productVariantId}")
    private ResponseEntity<?> getById(long productVariantId){
        ResProductVariantDto resProductVariantDto = productVariantService.getProductVariantById(productVariantId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductVariantDto
        );
        return  ResponseEntity.ok(responseData);
    }
    @PutMapping("/{productVariantId}")
    private ResponseEntity<?> update(long productVariantId, ReqProductVariantDto reqProductVariantDto){
        ResProductVariantDto resProductVariantDto = productVariantService.updateProductVariant(productVariantId,reqProductVariantDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data updated successfully",
                resProductVariantDto
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping
    private ResponseEntity<?> getByProductId(@RequestParam(name = "productId") long productId){
        List<ResProductVariantDto> resProductVariantDto = productVariantService.getAllProductVariantByProductId(productId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductVariantDto
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/count")
    private ResponseEntity<?> count(){
        Long sum = productVariantService.countProduct();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                sum
        );
        return ResponseEntity.ok(responseData);
    }
}
