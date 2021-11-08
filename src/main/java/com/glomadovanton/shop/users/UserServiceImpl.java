package com.glomadovanton.shop.users;

import com.glomadovanton.shop.rest.dto.orderRequest.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        List<UserEntity> userEntityList = userRepository.findAll();
    }
}
