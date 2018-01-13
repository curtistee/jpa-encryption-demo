package com.example.demo.repository;

import com.example.demo.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface JPAQueryRepository extends Repository<DemoEntity, String>, JpaSpecificationExecutor<DemoEntity> {
    
    List<DemoEntity> findAll();
}
