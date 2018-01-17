package com.example.demo.repository;

import com.example.demo.entity.DemoEntity;
import org.springframework.data.repository.CrudRepository;


public interface DemoEntityRepository extends CrudRepository<DemoEntity, String> {
    
    DemoEntity findByEncryptedField(String arg);
    
    DemoEntity findByEncryptedFieldEquals(String arg);

}
