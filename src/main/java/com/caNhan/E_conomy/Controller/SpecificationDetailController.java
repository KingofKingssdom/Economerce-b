package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.SpecificationDetailDTO;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.SpecificationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/specificationDetail")
public class SpecificationDetailController {
    private SpecificationDetailService specificationDetailService;
    @Autowired
    public SpecificationDetailController(SpecificationDetailService specificationDetailService) {
        this.specificationDetailService = specificationDetailService;
    }
    @PostMapping("/create")
    private ResponseEntity<?> addSpecificationDetail (SpecificationDetailDTO specificationDetailDTO) {
        SpecificationDetailDTO specificationDetailResponse = specificationDetailService.create(specificationDetailDTO);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Tạo thông số chi tiết sản phẩm thành công",
                specificationDetailResponse
        );
        return ResponseEntity.ok(responseData);
    }
}
