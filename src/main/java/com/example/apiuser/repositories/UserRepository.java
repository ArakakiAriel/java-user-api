package com.example.apiuser.repositories;
//It will make the connections to the DB using the model

import com.example.apiuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository //Using this notation and make this interface extend to CrudRepository<NameOfTheModel, IdType> we have it done
public interface UserRepository extends JpaRepository<UserModel, String> {

    @Query(value="SELECT u FROM users u WHERE u.email=?1")
    public UserModel findByEmail(String email);

}
