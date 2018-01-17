package com.example.demo.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DemoEntityThree {

    @Transient
    private static final Logger logger = LoggerFactory.getLogger(DemoEntityThree.class);

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
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

    public DemoEntityThree(String field) {
        logger.info("Setting field to {}", field);
        name = field;
    }

    public DemoEntityThree() {}

    @Override
    public String toString() {
        return String.format("%s: id=%d, field=%s", this.getClass().getSimpleName(), id, name);
    }

}
