package com.caNhan.E_conomy.Dto.ResponseDto;

import java.util.List;

public class ResUserDto {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private List<ResRoleDto> roles;

    public ResUserDto() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<ResRoleDto> getRoles() {
        return roles;
    }
    public void setRoles(List<ResRoleDto> roles) {
        this.roles = roles;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
