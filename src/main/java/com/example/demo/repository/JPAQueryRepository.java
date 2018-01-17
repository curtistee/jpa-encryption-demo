package com.example.demo.repository;

import com.example.demo.entity.DemoEntityThree;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface JPAQueryRepository extends Repository<DemoEntityThree, String>, JpaSpecificationExecutor<DemoEntityThree> {
    
    List<DemoEntityThree> findAll();
    
    DemoEntityThree save(DemoEntityThree entity);
}
