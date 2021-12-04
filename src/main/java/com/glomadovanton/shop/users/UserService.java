package com.glomadovanton.shop.users;

import com.glomadovanton.shop.exception.UserExistException;
import com.glomadovanton.shop.rest.dto.user.User;

public interface UserService {
   void addUser(User user) throws UserExistException;
   Long getUserId(String number);
}
