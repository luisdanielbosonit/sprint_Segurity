package com.bosonit.training.security.role.infrastructure.dtos;

import com.bosonit.training.security.role.entity.Role;
import lombok.Data;



@Data
public class RoleOUTputDto {

    public Integer idRole;
    public String nameRole;

    public RoleOUTputDto(Role role){
        this.idRole = role.getIdRole();
        this.nameRole=role.getNameRole();

    }
}