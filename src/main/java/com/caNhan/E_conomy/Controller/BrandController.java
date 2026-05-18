package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.RequestDto.ReqBrandDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResBrandDto;
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
    @PostMapping
    public ResponseEntity<?> create (@ModelAttribute ReqBrandDto reqBrandDto) {
        ResBrandDto resBrandDto = brandService.createBrand(reqBrandDto);
        ResponseData responseData = new ResponseData<>(
                HttpStatus.OK.value(),
                "Data created successfully",
                resBrandDto);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    private ResponseEntity<?> getAll () {
        List<ResBrandDto> resBrandDtoList = brandService.getAllBrand();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resBrandDtoList
        );
        return  ResponseEntity.ok(responseData);
    }

    @GetMapping("/brandCode/{brandCode}")
    public ResponseEntity<?> getByBrandCode(@PathVariable String brandCode){
        ResBrandDto resBrandDto = brandService.getBrandByBrandCode(brandCode);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resBrandDto
        );
        return  ResponseEntity.ok(responseData);
    }
    @GetMapping("categoryId/{categoryId}")
    public ResponseEntity<?> getByCategoryId(@PathVariable long categoryId){
        List<ResBrandDto> resBrandDtoList = brandService.getAllBrandByCategoryId(categoryId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resBrandDtoList
        );
        return  ResponseEntity.ok(responseData);
    }
    @PutMapping("/{brandId}")
    private ResponseEntity<?> update(@PathVariable Long brandId,
                                          @ModelAttribute ReqBrandDto reqBrandDto){
        ResBrandDto resBrandDto = brandService.updateBrand(brandId,reqBrandDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data updated successfully",
                resBrandDto
        );
        return ResponseEntity.ok(responseData);
    }
    @DeleteMapping("/{brandId}")
    public ResponseEntity<?> delete(@PathVariable long brandId){
        brandService.deleteBrand(brandId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data deleted successfully"
        );
        return ResponseEntity.ok(responseData);
    }



}
