package com.example.demo;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class JasyptConfigurator {

    private StandardPBEStringEncryptor defaultStringEncryptor;
    private StandardStringDigester passwordDigester;

    @PostConstruct
    public void configureJasypt() {
        
        String password = "secret";

        this.defaultStringEncryptor = new StandardPBEStringEncryptor();
        this.defaultStringEncryptor.setPassword(password);

        HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
        registry.registerPBEStringEncryptor("defaultStringEncryptor", defaultStringEncryptor);

        /*this.passwordDigester = new StandardStringDigester();
        //this.passwordDigester.setSaltGenerator(new StringFixedSaltGenerator(password));
        this.passwordDigester.setSaltSizeBytes(16);
        this.passwordDigester.setAlgorithm("SHA-512");
        this.passwordDigester.initialize();*/
    }
    
    @Bean
    public BasicTextEncryptor basicTextEncryptor() {
        return new BasicTextEncryptor();
    }
    
    @Bean
    public static StandardPBEStringEncryptor standardPBEStringEncryptor() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("secret");
        
        return standardPBEStringEncryptor;
    }
    
    @Bean
    public HibernatePBEStringEncryptor hibernateEncryptor() {
        HibernatePBEStringEncryptor hibernateEncryptor = new HibernatePBEStringEncryptor();
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("secret");
        hibernateEncryptor.setEncryptor(standardPBEStringEncryptor);
        hibernateEncryptor.setRegisteredName("hibernateEncryptor");
        
        return hibernateEncryptor;
    }
    
/*    @Bean
    public StandardStringDigester getPasswordDigester() {
        if (null == passwordDigester) {
            configureJasypt();
        }

        return passwordDigester;
    }*/
}

