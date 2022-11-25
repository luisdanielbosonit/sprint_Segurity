package com.bosonit.training.security.persona.entity;

import com.bosonit.training.security.role.entity.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "persona")
public class Persona  {

    private static final String fechtype = null;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPersona;

    private String name;

    @Column(length = 10,nullable = false)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();


}






