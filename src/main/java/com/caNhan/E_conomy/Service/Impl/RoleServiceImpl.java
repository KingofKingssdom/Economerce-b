package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ResponseDto.RoleResponseDTO;
import com.caNhan.E_conomy.Dto.RoleDTO;
import com.caNhan.E_conomy.Entity.Roles;
import com.caNhan.E_conomy.Repository.RoleRepository;
import com.caNhan.E_conomy.Service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponseDTO create(RoleDTO roleDTO) {
        Roles roles = new Roles();
        roles.setRoleName(roleDTO.getRoleName());
        Roles saveRole = roleRepository.save(roles);
        return modelMapper.map(saveRole, RoleResponseDTO.class);
    }

    @Override
    public RoleResponseDTO readAll() {
        return null;
    }
}
