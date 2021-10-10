package com.major.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.major.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
