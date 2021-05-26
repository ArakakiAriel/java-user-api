package com.example.apiuser.repositories;
//It will make the connections to the DB using the model
//On interfaces classes all the functions are public and abstract so we don't need to declare that

import com.example.apiuser.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //Using this notation and make this interface extend to CrudRepository<NameOfTheModel, IdType> we have it done
public interface UserRepository extends CrudRepository<UserModel, String> {

    //You can also do a custom query with @Query
    /*@Query(value="SELECT u FROM users u WHERE u.email=?1")
    UserModel findByEmail(String email);*/

    //CrudRepository is intelligent to understand the new creation of custom functions with this look
    UserModel findByEmail(String email);

    UserModel findByStatus(String status);



}
