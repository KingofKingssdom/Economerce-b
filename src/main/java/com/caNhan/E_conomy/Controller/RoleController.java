package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ResponseDto.RoleResponseDTO;
import com.caNhan.E_conomy.Dto.RoleDTO;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping("/create")
    private ResponseEntity<?> create(@ModelAttribute RoleDTO roleDTO){
        RoleResponseDTO responseDTO = roleService.create(roleDTO);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Tạo role thành công",
                responseDTO
        );
        return ResponseEntity.ok(responseData);
    }
}
