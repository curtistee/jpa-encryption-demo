package com.example.demo.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class AnotherDemoEntity {

    @Transient
    private static final Logger logger = LoggerFactory.getLogger(AnotherDemoEntity.class);

    @Id
    @GeneratedValue
    private Long id;

    @Convert(converter = EntityAttributeConverter.class)
    private String name;

    public void setName(String arg) {
        logger.info("Setting field value to {}", arg);
        name = arg;
    }

    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }

    public AnotherDemoEntity(String field) {
        logger.info("Setting field to {}", field);
        name = field;
    }

    public AnotherDemoEntity() {}

    @Override
    public String toString() {
        return String.format("%s: id=%d, field=%s", this.getClass().getSimpleName(), id, name);
    }
}
