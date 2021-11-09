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
    public UserEntity addUser(User user) {
        if (!userRepository.existsByNumber(user.getNumber())){
            UserEntity userEntity = new UserEntity();
            userEntity.setNumber(user.getNumber());
            userEntity.setName(user.getName());
            userRepository.saveAndFlush(userEntity);
        }
        return userRepository.findUserEntityByNumber(user.getNumber());
    }
}
