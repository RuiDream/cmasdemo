package com.example.demo.service.impl;


import Temporary.UserLogin;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel findByUserNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean findUserByName(String username) {
        UserModel u =null;
        Optional<UserModel> ou = userRepository.findByUsername(username);
        if(ou.isPresent()){ //判断Optional是否包含用户对象
//            u = ou.get();//获得ou对象
            return true;
        }
        return false;
    }

    @Override
    public  <S extends UserModel> boolean addUser(S u) {
        return (userRepository.save(u).getUsername() != null);
    }

    @Override
    public UserModel checkUser(UserLogin user) {
        UserModel u =null;
        //去数据库中通过username查找用户信息
        Optional<UserModel> ou = userRepository.findByUsername(user.getUsername());
        if(ou.isPresent()){ //判断Optional是否包含用户对象
            u = ou.get();//获得ou对象
            if(u.getPassword().equals(user.getPassword())){
                return u;
            }
        }
        return null;
    }
//}
}
