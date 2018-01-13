package com.example.demo.repository;

import com.example.demo.entity.AnotherDemoEntity;
import org.springframework.data.repository.CrudRepository;


public interface AnotherDemoEntityRepository extends CrudRepository<AnotherDemoEntity, String> {

    AnotherDemoEntity findByName(String arg);
    
    AnotherDemoEntity findByUnencryptedName(String arg);

}
