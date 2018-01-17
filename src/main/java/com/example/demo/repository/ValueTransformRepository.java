package com.example.demo.repository;

import com.example.demo.JasyptConfigurator;
import com.example.demo.entity.DemoEntityThree;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class ValueTransformRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(ValueTransformRepository.class);
    private final StandardPBEStringEncryptor standardPBESStringEncryptor = JasyptConfigurator.standardPBEStringEncryptor();
    @Autowired
    WrappedJPARepository wrappedJPARepository;
    
    public DemoEntityThree findByEncryptedName(String name) {
        String encryptedName = standardPBESStringEncryptor.encrypt(name);
        logger.info("Finding entity with unencrypted value {} ({})", name, encryptedName);
        
        return wrappedJPARepository.findByName(encryptedName);
        
    }
    
    public DemoEntityThree testSave(DemoEntityThree entity) {
        entity.setName(standardPBESStringEncryptor.encrypt(entity.getName()));
        
        return wrappedJPARepository.save(entity);
    }

}
