package com.glomadovanton.shop.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    boolean existsByNumber(String number);

    UserEntity findUserEntityByNumber(String number);

}

