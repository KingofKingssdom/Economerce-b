package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ResponseDto.UserResponseDTO;
import com.caNhan.E_conomy.Dto.UserDto;

public interface UserService {
    UserResponseDTO create(UserDto userDto);
//    UserResponseDTO createAdmin(UserDto userDto);
}
