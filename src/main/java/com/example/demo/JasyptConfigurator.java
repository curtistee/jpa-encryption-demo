package com.example.demo;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JasyptConfigurator {
    
    @Bean
    public BasicTextEncryptor basicTextEncryptor() {
        return new BasicTextEncryptor();
    }
    
    @Bean
    public static StandardPBEStringEncryptor standardPBEStringEncryptor() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("secret");
        standardPBEStringEncryptor.setSaltGenerator(new StringFixedSaltGenerator("lk::as'9124 sa", "UTF-8"));
        
        return standardPBEStringEncryptor;
    }
    
    @Bean
    public HibernatePBEStringEncryptor hibernateEncryptor() {
        HibernatePBEStringEncryptor hibernateEncryptor = new HibernatePBEStringEncryptor();
        StandardPBEStringEncryptor standardPBEStringEncryptor = JasyptConfigurator.standardPBEStringEncryptor();
        
        hibernateEncryptor.setEncryptor(standardPBEStringEncryptor);
        hibernateEncryptor.setRegisteredName("hibernateEncryptor");
        
        return hibernateEncryptor;
    }
    
}

