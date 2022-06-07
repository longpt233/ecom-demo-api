package com.company.team.repository;

import com.company.team.data.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Long> {

    @Query(value = "SELECT * FROM cart_tb c " +
            "WHERE :username IS NULL OR c.cart_username = :username " +
            "ORDER BY c.cart_created_at DESC LIMIT 1",nativeQuery = true)
    CartEntity findLatestCartByUsername(@Param("username") String id_user);

}
