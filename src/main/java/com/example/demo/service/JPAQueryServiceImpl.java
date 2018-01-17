package com.example.demo.service;

import com.example.demo.JasyptConfigurator;
import com.example.demo.entity.DemoEntityThree;
import com.example.demo.repository.JPAQueryRepository;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class JPAQueryServiceImpl implements JPAQueryService {
    
    private final JPAQueryRepository jpaQueryRepository;
    static final StringEncryptor standardPBEStringEncryptor = JasyptConfigurator.standardPBEStringEncryptor();
    
    @Autowired
    public JPAQueryServiceImpl(JPAQueryRepository jpaQueryRepository) {
        this.jpaQueryRepository = jpaQueryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public DemoEntityThree findByUnencryptedValue(String value) {
        List<DemoEntityThree> entityList = jpaQueryRepository.findAll(DemoEntitySpecifications.findByManualEncryption(value));
        return entityList.isEmpty() ? null : entityList.get(0);
    }
    
    
    @Transactional
    @Override
    public DemoEntityThree save(DemoEntityThree entity) {
        String encryptedName = standardPBEStringEncryptor.encrypt(entity.getName());
        entity.setName(encryptedName);
        jpaQueryRepository.save(entity);
        
        return entity;
    }

    final static class DemoEntitySpecifications {
        private static final Logger logger = LoggerFactory.getLogger(DemoEntitySpecifications.class);
        private DemoEntitySpecifications() {}

        static Specification<DemoEntityThree> findByManualEncryption(String field) {

            return (root, query, cb) -> {
                String encryptedFieldValue = standardPBEStringEncryptor.encrypt(field);
                logger.info("name: {}, encrypted: {}", root.get("name").toString(), encryptedFieldValue);
                return cb.equal(root.get("name"), encryptedFieldValue);
            };

        }

    }
}
