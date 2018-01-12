package com.example.demo.entity;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class DemoEntity {
    
    @Transient
    private static final Logger logger = LoggerFactory.getLogger(DemoEntity.class);

    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "encryptedString")
    @Column(name = "test_field")
    private String encryptedField;
    
    public void setEncryptedField(String arg) {
        logger.info("Setting field value to {}", arg);
        encryptedField = arg;
    }
    
    public String getEncryptedField() {
        return encryptedField;
    }

    public Long getId() {
        return id;
    }
    
    public DemoEntity(String field) {
        logger.info("Setting field to {}", field);
        encryptedField = field;
    }
    
    public DemoEntity() {}
    
    @Override
    public String toString() {
        return String.format("%s: id=%d, field=%s", this.getClass().getSimpleName(), id, encryptedField);
    }

}
