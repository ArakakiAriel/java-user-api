package com.example.apiuser.repositories;

import com.example.apiuser.models.RoleModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<RoleModel, Long> {

    RoleModel findByRoleName(String roleName);

}
