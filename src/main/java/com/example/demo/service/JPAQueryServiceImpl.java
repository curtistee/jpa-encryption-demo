package com.example.demo.service;

import com.example.demo.JasyptConfigurator;
import com.example.demo.entity.DemoEntity;
import com.example.demo.repository.JPAQueryRepository;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class JPAQueryServiceImpl implements JPAQueryService {
    
    private final JPAQueryRepository jpaQueryRepository;
    
    @Autowired
    public JPAQueryServiceImpl(JPAQueryRepository jpaQueryRepository) {
        this.jpaQueryRepository = jpaQueryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public DemoEntity findByUnencryptedValue(String value) {
        List<DemoEntity> entityList = jpaQueryRepository.findAll(DemoEntitySpecifications.findByManualEncryption(value));
        return entityList.isEmpty() ? null : entityList.get(0);
    }

    final static class DemoEntitySpecifications {

        private DemoEntitySpecifications() {}

        static final StringEncryptor standardPBEStringEncryptor = JasyptConfigurator.standardPBEStringEncryptor();

        static Specification<DemoEntity> findByManualEncryption(String field) {

            return (root, query, cb) -> {
                String encryptedFieldValue = encryptFieldValue(field);
                
                return cb.equal(root.get("encryptedField"), encryptedFieldValue);
            };

        }

        private static String encryptFieldValue(String field) {
            if (field == null || field.isEmpty()) {
                return "";
            }

            return standardPBEStringEncryptor.encrypt(field);

        }

    }
}
