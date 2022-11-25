package com.bosonit.training.security.role.service;

import com.bosonit.training.security.role.infrastructure.dtos.RoleINputDto;
import com.bosonit.training.security.role.infrastructure.dtos.RoleOUTputDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceRole {


    public List<RoleOUTputDto> viewRole(String nameRole) throws Exception ; //ok


    public void delete(Integer id) throws Exception; //ok

    public List<RoleOUTputDto> viewall();

    public RoleOUTputDto loadRole(RoleINputDto roleINputDto)throws Exception; //ok

    void addRolePersona(String name, String nameRole);

    void addRoleToPerson(String username, String roleName);
}
