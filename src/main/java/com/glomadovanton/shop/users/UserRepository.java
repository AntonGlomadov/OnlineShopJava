package com.glomadovanton.shop.users;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity,Long> {

    boolean findByNumber(String number);
}

