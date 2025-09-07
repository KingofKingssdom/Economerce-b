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
    public ResponseEntity<ResponseData<Brand>> addBrand (@ModelAttribute BrandDTO brandDTO) {
        Brand brand = brandService.create(brandDTO);
        ResponseData<Brand> responseData = new ResponseData<>(
                HttpStatus.OK.value(),
                "Tạo nhãn hieu thành công",
                brand);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/getAll")
    private ResponseEntity<?> getAllBrand () {
        List<Brand> brand = brandService.realAll();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy nhãn hiệu thành công",
                brand
        );
        return  ResponseEntity.ok(responseData);
    }
//    @GetMapping("{id}")
//    public ResponseEntity<Brand> getBrandById(@PathVariable int id){
//        Brand brand = brandService.findBrandById(id);
//        return ResponseEntity.ok(brand);
//    }
//
//    @GetMapping("/category")
//    public ResponseEntity<?> getByCategoryId(@RequestParam(value = "categoryId") Integer categoryId){
//        List<Brand> brands = brandService.findBrandByCategoryId(categoryId);
//        return ResponseEntity.ok(brands);
//    }
//
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateBrandById(@PathVariable int id,
//                                             @RequestParam (value = "brandName") String brandName,
//                                             @RequestParam (value = "imageBrand", required = false) MultipartFile imageBrand,
//                                             @RequestParam (value = "categoryIds", required = false) List<Integer> categoryIds) throws Exception {
//        try {
//           Brand brand = brandService.findBrandById(id);
//           if(brand != null){
//               brand.setBrandName(brandName);
//
//           }
//           Brand addBrand = brandService.saveBrand(brand,imageBrand,categoryIds);
//            return ResponseEntity.ok(addBrand);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.badRequest().build();
//    }

}
