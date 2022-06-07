package com.company.team.repository;

import com.company.team.data.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    //    @Query(value = "select from categories where category_name like %?%",
//            nativeQuery = true)
    CategoryEntity findAllByName(String name);

}
