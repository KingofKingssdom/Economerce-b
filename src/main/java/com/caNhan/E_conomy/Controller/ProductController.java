package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ProductDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductResponseDTO;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
       ProductResponseDTO product = productService.create(productDTO);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Tạo sản phẩm thành công",
                product
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/search/all")
    private ResponseEntity<?> getAllProduct(@RequestParam (defaultValue = "0") int pageNumber,
                                            @RequestParam (defaultValue = "8") int pageSize){
        Page<ProductResponseDTO> products = productService.readAll(pageNumber, pageSize);
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
    @GetMapping("/search/category")
    private ResponseEntity<?> getByCategoryId(@RequestParam(value = "categoryId") Long categoryId,
                                              @RequestParam(defaultValue = "0") int pageNumber,
                                              @RequestParam(defaultValue = "8") int pageSize){
        Page<ProductResponseDTO> products = productService.readByCategory(categoryId, pageNumber, pageSize);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy sản phẩm theo danh mục thành công",
                products
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/get/category/brand")
    private ResponseEntity<?> getProductByCategoryIdAndBrandId(@RequestParam(value = "categoryId") Long categoryId,
                                                               @RequestParam(value = "brandId") Long brandId,
                                                               @RequestParam(defaultValue = "0") int pageNumber,
                                                               @RequestParam(defaultValue = "8") int pageSize) {
      Page<ProductResponseDTO> productResponseDTOS = productService.readByCategoryAndBrand(categoryId, brandId, pageNumber, pageSize);
      ResponseData responseData = new ResponseData(
              HttpStatus.OK.value(),
              "Lấy sản phẩm theo danh mục và nhãn hiệu thành công",
              productResponseDTOS
      );
      return ResponseEntity.ok(responseData);
    }

    @GetMapping("/search/featured")
    private ResponseEntity<?> readAllByFeatured (@RequestParam (name = "featured") boolean featured) {
        List<ProductResponseDTO> productResponseDTOS = productService.readAllByFeatured(featured);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy sản phẩm theo nôi bật thành công",
                productResponseDTOS
        );
        return  ResponseEntity.ok(responseData);
    }
    @GetMapping("/search/productName")
    private ResponseEntity<?> readAllByProductName (@RequestParam (name = "productName") String productName){
        List<ProductResponseDTO> productResponseDTOS = productService.readByProductName(productName);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy sản phẩm theo tên thành công",
                productResponseDTOS
        );
        return ResponseEntity.ok(responseData);
    }
}
