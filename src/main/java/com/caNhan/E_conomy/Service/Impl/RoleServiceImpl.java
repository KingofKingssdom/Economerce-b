package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ResponseDto.ResRoleDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqRoleDto;
import com.caNhan.E_conomy.Entity.Roles;
import com.caNhan.E_conomy.Repository.RoleRepository;
import com.caNhan.E_conomy.Service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResRoleDto createRole(ReqRoleDto reqRoleDto) {
        Roles roles = new Roles();
        roles.setRoleName(reqRoleDto.getRoleName());
        Roles saveRole = roleRepository.save(roles);
        return modelMapper.map(saveRole, ResRoleDto.class);
    }

    @Override
    public List<ResRoleDto> getAllRole() {
       List<Roles> roleList = roleRepository.findAll();
       List<ResRoleDto> resRoleDtoList = roleList.stream()
               .map(roles -> modelMapper.map(roles, ResRoleDto.class))
               .toList();
       return resRoleDtoList;
    }
}
