package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.BrandDTO;
import com.caNhan.E_conomy.Entity.Brand;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.Impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private BrandServiceImpl brandService;
    @Autowired
    public BrandController(BrandServiceImpl brandService) {
        this.brandService = brandService;

    }
    @PostMapping("/create")
    public ResponseEntity<?> addBrand (@ModelAttribute BrandDTO brandDTO) {
        Brand brand = brandService.create(brandDTO);
        ResponseData responseData = new ResponseData<>(
                HttpStatus.OK.value(),
                "Tạo nhãn hieu thành công",
                brand);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/get/all")
    private ResponseEntity<?> getAllBrand () {
        List<Brand> brand = brandService.realAll();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy nhãn hiệu thành công",
                brand
        );
        return  ResponseEntity.ok(responseData);
    }

    @GetMapping("/get/category")
    private ResponseEntity<?> getBrandByCategory(@RequestParam(name = "categoryId") Long categoryId) {
        List<Brand> brands = brandService.readByCategoryId(categoryId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy nhãn hàng theo danh mục thành công",
                brands
        );
        return ResponseEntity.ok(responseData);
    }


}
