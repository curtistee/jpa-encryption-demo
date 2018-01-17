package com.example.demo.repository;

import com.example.demo.entity.DemoEntityFive;
import org.springframework.data.repository.CrudRepository;


public interface ColumnConverterDemoEntityRepository extends CrudRepository<DemoEntityFive, Long> {

    DemoEntityFive findDemoEntityByEncryptedField(String arg);

}
