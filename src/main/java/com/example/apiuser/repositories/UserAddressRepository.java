/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiuser.repositories;

import com.example.apiuser.models.UserAddressModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface UserAddressRepository extends CrudRepository<UserAddressModel, String>{
    //UserAddressModel findByUserAddressID(String id);

}
