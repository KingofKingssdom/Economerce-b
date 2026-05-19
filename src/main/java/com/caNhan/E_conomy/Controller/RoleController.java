package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ResponseDto.ResRoleDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqRoleDto;
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
    @PostMapping
    private ResponseEntity<?> create(@ModelAttribute ReqRoleDto reqRoleDto){
        ResRoleDto resRoleDto = roleService.createRole(reqRoleDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data created successfully",
                resRoleDto
        );
        return ResponseEntity.ok(responseData);
    }
}
