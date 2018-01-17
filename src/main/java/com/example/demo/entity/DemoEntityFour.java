package com.example.demo.entity;

import com.example.demo.JasyptConfigurator;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DemoEntityFour {

    @Transient
    private static final Logger logger = LoggerFactory.getLogger(DemoEntityFour.class);
    @Transient
    private final StandardPBEStringEncryptor standardPBEStringEncryptor = JasyptConfigurator.standardPBEStringEncryptor();

    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    public void setName(String name) {
        this.name = standardPBEStringEncryptor.encrypt(name);
    }
    
    public String getName() {
        return standardPBEStringEncryptor.decrypt(this.name);
    }
    
    public DemoEntityFour() {}
    
    public DemoEntityFour(String name) {
        logger.info("Creating new {} with name '{}'", this.getClass().getSimpleName(), name);
        this.name = standardPBEStringEncryptor.encrypt(name);
    }

    @Override
    public String toString() {
        return String.format("%s: id=%d, name=%s, encrypted=%s", this.getClass().getSimpleName(), id, this.getName(), this.name);
    }

}
