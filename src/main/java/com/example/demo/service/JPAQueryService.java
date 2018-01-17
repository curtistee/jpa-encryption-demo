package com.example.demo.service;

import com.example.demo.entity.DemoEntityThree;


public interface JPAQueryService {

    DemoEntityThree findByUnencryptedValue(String value);
    
    DemoEntityThree save(DemoEntityThree entity);
    
}
