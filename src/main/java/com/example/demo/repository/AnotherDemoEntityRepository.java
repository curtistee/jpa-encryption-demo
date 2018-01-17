package com.example.demo.repository;

import com.example.demo.entity.DemoEntityTwo;
import org.springframework.data.repository.CrudRepository;


public interface AnotherDemoEntityRepository extends CrudRepository<DemoEntityTwo, Long> {

    DemoEntityTwo findByName(String arg);
    
    DemoEntityTwo findByUnencryptedName(String arg);

}
