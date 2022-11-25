package com.bosonit.training.security.role.infrastructure.dtos;
import com.bosonit.training.security.role.entity.Role;
import lombok.Data;


@Data
public class RoleINputDto {

    public Integer idRole;
    public String nameRole;


    public Role transformIntoRole() {
        Role role = new Role();
        role.setIdRole(this.idRole);
        role.setNameRole(this.nameRole);
        return role;
    }

    public RoleINputDto(Integer idRole, String nameRole) {
        this.idRole= idRole;
        this.nameRole = nameRole;
    }
}

