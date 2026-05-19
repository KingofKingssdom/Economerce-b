package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ResponseDto.ResRoleDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqRoleDto;

import java.util.List;

public interface RoleService {
    ResRoleDto createRole(ReqRoleDto reqRoleDto);
    List<ResRoleDto> getAllRole();
}
