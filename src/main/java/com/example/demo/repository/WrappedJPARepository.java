package com.example.demo.repository;

import com.example.demo.entity.DemoEntityThree;
import org.springframework.data.repository.CrudRepository;


public interface WrappedJPARepository extends CrudRepository<DemoEntityThree, Long> {
    
    DemoEntityThree findByName(String arg);
    
}
