package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Entity.Brand;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Service.BrandService;
import com.caNhan.E_conomy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    private BrandService brandService;
    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;

    }
    @PostMapping("/add")
    public ResponseEntity<?> addBrand (@RequestParam String brandName,
                         @RequestParam(value = "imageBrand", required = false) MultipartFile imageBrand,
                          @RequestParam(value = "categoryIds", required = false) List<Integer> categoryIds) throws Exception {
        try {
            Brand brand = new Brand();
            brand.setBrandName(brandName);
            brand.setImageBrand(imageBrand.getBytes());
            Brand addBrand = brandService.saveBrand(brand, imageBrand,categoryIds);
            return ResponseEntity.ok(addBrand);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Failed to upload avatar");
    }

    @GetMapping("/getAll")
    public List<Brand> getAllBrand () {
        return  brandService.findAllBrand();
    }
    @GetMapping("{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable int id){
        Brand brand = brandService.findBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getByCategoryId(@RequestParam(value = "categoryId") Integer categoryId){
        List<Brand> brands = brandService.findBrandByCategoryId(categoryId);
        return ResponseEntity.ok(brands);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBrandById(@PathVariable int id,
                                             @RequestParam (value = "brandName") String brandName,
                                             @RequestParam (value = "imageBrand", required = false) MultipartFile imageBrand,
                                             @RequestParam (value = "categoryIds", required = false) List<Integer> categoryIds) throws Exception {
        try {
           Brand brand = brandService.findBrandById(id);
           if(brand != null){
               brand.setBrandName(brandName);

           }
           Brand addBrand = brandService.saveBrand(brand,imageBrand,categoryIds);
            return ResponseEntity.ok(addBrand);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

}
