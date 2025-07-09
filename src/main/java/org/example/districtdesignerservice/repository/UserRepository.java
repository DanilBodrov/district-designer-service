package org.example.districtdesignerservice.repository;

import org.example.districtdesignerservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    User getUserByEmailAndPassword(String email, String password);
}
