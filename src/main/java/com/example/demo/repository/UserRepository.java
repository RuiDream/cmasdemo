package com.example.demo.repository;

import com.example.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsernameAndPassword(String username, String password);
//    UserModel findByUsername(String username);//返回对象
    Optional<UserModel> findByUsername(String username);

}
