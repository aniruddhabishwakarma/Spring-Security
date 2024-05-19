package com.example.SpringSec.repository;

import com.example.SpringSec.entity.Roles;
import com.example.SpringSec.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);
}
