package com.example.demo.entity;

import org.hibernate.annotations.ColumnTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DemoEntityFive {

    @Transient
    private static final Logger logger = LoggerFactory.getLogger(DemoEntityFive.class);

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    @ColumnTransformer(read = "AES_DECRYPT(encrypted_field, HEX(SHA2('secret',512)))", write = "AES_ENCRYPT(?, HEX(SHA2('secret',512)))")
    protected String encryptedField;

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

    public DemoEntityFive(String field) {
        logger.info("Setting field to {}", field);
        encryptedField = field;
    }

    public DemoEntityFive() {}

    @Override
    public String toString() {
        return String.format("%s: id=%d, field=%s", this.getClass().getSimpleName(), id, encryptedField);
    }

}
