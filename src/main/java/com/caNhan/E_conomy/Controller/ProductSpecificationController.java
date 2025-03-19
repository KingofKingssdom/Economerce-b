package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Entity.ProductSpecification;
import com.caNhan.E_conomy.Service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productSpecification")
public class ProductSpecificationController {
    private ProductSpecificationService productSpecificationService;
    @Autowired
    public ProductSpecificationController(ProductSpecificationService productSpecificationService) {
        this.productSpecificationService = productSpecificationService;
    }
    @PostMapping("/add")
    public ProductSpecification addProductSpecification (@RequestBody ProductSpecification productSpecification){
        return productSpecificationService.createProductSpecification(productSpecification);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductSpecification> getProductSpecificationById (@PathVariable int id) {
        ProductSpecification productSpecification = productSpecificationService.findById(id);
        return ResponseEntity.ok(productSpecification);
    }
    @GetMapping("/getAll")
    public List<ProductSpecification> getAll () {
        return productSpecificationService.findAll();
    }
    @PutMapping("{id}")
    public ResponseEntity<ProductSpecification> updateProductSpecification (@PathVariable int id, @RequestBody ProductSpecification productSpecificationDetails) {
        ProductSpecification productSpecification = productSpecificationService.findById(id);
        productSpecification.setSpecificationName(productSpecificationDetails.getSpecificationName());
        productSpecification.setSpecificationValue(productSpecificationDetails.getSpecificationValue());
        productSpecificationService.createProductSpecification(productSpecification);
        return ResponseEntity.ok(productSpecification);
    }

}
