package com.caNhan.E_conomy.Dto.ResponseDto;

public class RoleResponseDTO {
    private Long id;
    private String roleName;

    public RoleResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

