package com.example.demo.repository;

import com.example.demo.entity.DemoEntityFour;
import org.springframework.data.repository.CrudRepository;


public interface YetAnotherDemoEntityRepository extends CrudRepository<DemoEntityFour, Long> {
    
    DemoEntityFour findByName(String name);

}
