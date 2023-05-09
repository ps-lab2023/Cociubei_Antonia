package com.example.bookstore.repositories;

import com.example.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByNameAndPassword(String name, String password);
    User findByEmail(String email);
}
