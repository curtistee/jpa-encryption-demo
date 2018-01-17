package com.example.demo.entity;

import com.example.demo.JasyptConfigurator;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class EntityAttributeConverter implements AttributeConverter<String, String> {
    
    private StringEncryptor standardPBEStringEncryptor = JasyptConfigurator.standardPBEStringEncryptor();
    private static final Logger logger = LoggerFactory.getLogger(EntityAttributeConverter.class);

    @Override
    public String convertToDatabaseColumn(String attribute) {
        String encrypted = standardPBEStringEncryptor.encrypt(attribute);
        logger.info("{}: Encrypted '{}' to '{}'", this.getClass().getSimpleName(), attribute, encrypted);
        
        return encrypted;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        String decrypted = standardPBEStringEncryptor.decrypt(dbData);
        logger.info("{}: Decrypted '{}' to '{}'", this.getClass().getSimpleName(), dbData, decrypted);
        
        return decrypted;
    }

}
