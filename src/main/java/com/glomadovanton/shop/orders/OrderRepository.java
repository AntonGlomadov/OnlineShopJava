package com.glomadovanton.shop.orders;

import jdk.jshell.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    @Transactional
    @Modifying
    @Query("update OrderEntity u set u.status = :status where u.id = :id")
    int updateStatus(@Param(value = "id") Long id, @Param(value = "status") OrderStatus status);

}


