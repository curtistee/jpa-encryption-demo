package com.example.demo.repository;

import com.example.demo.entity.AnotherDemoEntity;
import org.springframework.data.repository.CrudRepository;


public interface AnotherDemoEntityRepository extends CrudRepository<AnotherDemoEntity, Long> {

    AnotherDemoEntity findAnotherDemoEntityByName(String arg);

}
