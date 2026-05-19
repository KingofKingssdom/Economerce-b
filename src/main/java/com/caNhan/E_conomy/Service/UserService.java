package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ResponseDto.ResUserDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqUserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResUserDto createUser(ReqUserDto reqUserDto,String roleName);

}
