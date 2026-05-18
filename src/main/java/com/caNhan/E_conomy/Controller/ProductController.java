package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.RequestDto.ReqProductDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductDto;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @PostMapping
    private ResponseEntity<?> create(@ModelAttribute ReqProductDto reqProductDto)  {
       ResProductDto resProductDto = productService.createProduct(reqProductDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data created successfully",
                resProductDto
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping
    private ResponseEntity<?> getAll(){
        List<ResProductDto> resProductDtoList = productService.getAllProduct();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductDtoList
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/productCode/{productCode}")
    private ResponseEntity<?> getByProductCode(@PathVariable String productCode){
        ResProductDto resProductDto = productService.getProductByProductCode(productCode);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductDto
        );
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{productId}")
    private ResponseEntity<?> update(@PathVariable long productId,
                                     @ModelAttribute ReqProductDto reqProductDto){
        ResProductDto resProductDto = productService.updateProduct(productId, reqProductDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Cập nhập sản phẩm thành cong",
                resProductDto
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/categoryId/{categoryId}")
    private ResponseEntity<?> getByCategoryId(@PathVariable long categoryId){
        List<ResProductDto> resProductDtoList = productService.getAllProductByCategory(categoryId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductDtoList
        );
        return ResponseEntity.ok(responseData);
    }
//    @GetMapping("/get/category/brand")
//    private ResponseEntity<?> getProductByCategoryIdAndBrandId(@RequestParam(value = "categoryId") Long categoryId,
//                                                               @RequestParam(value = "brandId") Long brandId,
//                                                               @RequestParam(defaultValue = "0") int pageNumber,
//                                                               @RequestParam(defaultValue = "8") int pageSize) {
//      Page<ResProductDto> productResponseDTOS = productService.readByCategoryAndBrand(categoryId, brandId, pageNumber, pageSize);
//      ResponseData responseData = new ResponseData(
//              HttpStatus.OK.value(),
//              "Lấy sản phẩm theo danh mục và nhãn hiệu thành công",
//              productResponseDTOS
//      );
//      return ResponseEntity.ok(responseData);
//    }

    @GetMapping("/feature")
    private ResponseEntity<?> getAllByFeatured (@RequestParam (name = "featured") boolean featured) {
        List<ResProductDto> resProductDtoList = productService.getAllProductByFeatured(featured);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductDtoList
        );
        return  ResponseEntity.ok(responseData);
    }
    @GetMapping("/promotional")
    private ResponseEntity<?> readAllByPromotionalAndCategory (@RequestParam (name = "promotional") boolean promotional) {
        List<ResProductDto> resProductDtoList = productService.getAllByPromotional(promotional);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductDtoList
        );
        return  ResponseEntity.ok(responseData);
    }
    @GetMapping("/productName")
    private ResponseEntity<?> readAllByProductName (@RequestParam (name = "productName") String productName){
        List<ResProductDto> resProductDtoList = productService.getProductByProductName(productName);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductDtoList
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/category-brand")
    private  ResponseEntity<?> getByCategoryAndBrand(@RequestParam(name = "categoryId")long categoryId,
                                                     @RequestParam(name = "brandId") long brandId){
        List<ResProductDto> resProductDtoList = productService.getAllProductByCategoryAndBrand(categoryId, brandId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resProductDtoList
        );
        return ResponseEntity.ok(responseData);
    }
}
