package com.example.demo.service;

import com.example.demo.entity.DemoEntity;


public interface JPAQueryService {

    DemoEntity findByUnencryptedValue(String value);
    
}
