package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ProductDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductResponseDTO;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @PostMapping("/create")
    private ResponseEntity<?> addProduct(@ModelAttribute ProductDTO productDTO)  {
       Product product = productService.create(productDTO);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Tạo sản phẩm thành công",
                product
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/search/all")
    private ResponseEntity<?> getAllProduct(){
        List<ProductResponseDTO> products = productService.readAll();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy toàn bộ sản phẩm thành công",
                products
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/search")
    private ResponseEntity<?> searchById(@RequestParam(name = "productId")Long productId){
        ProductResponseDTO productResponseDTO = productService.readById(productId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy sản phẩm theo id thanh công",
                productResponseDTO
        );
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update")
    private ResponseEntity<?> update(@RequestParam(name = "productId") Long categoryId,
                                     @ModelAttribute ProductDTO productDTO){
        ProductResponseDTO productResponseDTO = productService.update(categoryId, productDTO);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Cập nhập sản phẩm thành cong",
                productResponseDTO
        );
        return ResponseEntity.ok(responseData);
    }
}
