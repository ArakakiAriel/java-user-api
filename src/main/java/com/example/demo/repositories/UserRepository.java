package com.example.demo.repositories;
//It will make the connections to the DB using the model

import com.example.demo.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //Using this notation and make this interface extend to CrudRepository<NameOfTheModel, IdType> we have it done
public interface UserRepository extends CrudRepository<UserModel, String>{

}
