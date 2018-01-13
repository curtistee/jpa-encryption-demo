package com.example.demo.repository;

import com.example.demo.entity.YetAnotherDemoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface YetAnotherDemoEntityRepository extends CrudRepository<YetAnotherDemoEntity, String> {

    @Query("SELECT t FROM YetAnotherDemoEntity t WHERE t.name = ?1")
    YetAnotherDemoEntity findByName(String arg);

}
