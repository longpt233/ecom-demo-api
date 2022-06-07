package com.company.team.repository;

import com.company.team.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
    @Query(value="SELECT * FROM order_tb o " +
            "WHERE :userName IS NULL OR o.username = :userName",nativeQuery = true)
    List<OrderEntity> findOrderByUserName( @Param("userName") String userName);

}
