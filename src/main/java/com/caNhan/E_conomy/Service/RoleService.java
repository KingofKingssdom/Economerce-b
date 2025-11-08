package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ResponseDto.RoleResponseDTO;
import com.caNhan.E_conomy.Dto.RoleDTO;

public interface RoleService {
    RoleResponseDTO create(RoleDTO roleDTO);
    RoleResponseDTO readAll();
}
