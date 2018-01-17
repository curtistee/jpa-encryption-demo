package com.example.demo.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/* Class using a Hibernate EntityAttributeConverter */
@Entity
public class DemoEntityTwo {

    @Transient
    private static final Logger logger = LoggerFactory.getLogger(DemoEntityTwo.class);

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    @Convert(converter = EntityAttributeConverter.class)
    private String name;
    
    private String unencryptedName;

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
    
    public String getUnencryptedName() {
        return unencryptedName;
    }

    public DemoEntityTwo(String field) {
        logger.info("Setting field to {}", field);
        name = field;
        unencryptedName = field;
    }

    public DemoEntityTwo() {}

    @Override
    public String toString() {
        return String.format("%s: id=%d, name=%s, unencryptedName=%s", this.getClass().getSimpleName(), id, name, unencryptedName);
    }
}
