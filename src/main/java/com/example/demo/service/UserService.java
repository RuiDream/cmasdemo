package com.example.demo.service;


import Temporary.UserLogin;
import com.example.demo.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserModel findByUserNameAndPassword(String username, String password);
    boolean findUserByName(String username);
    public UserModel checkUser(UserLogin user);
    <S extends UserModel> boolean addUser(S u);
}

