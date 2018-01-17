package com.example.demo;

import com.example.demo.entity.DemoEntity;
import com.example.demo.entity.DemoEntityFive;
import com.example.demo.entity.DemoEntityFour;
import com.example.demo.entity.DemoEntityThree;
import com.example.demo.entity.DemoEntityTwo;
import com.example.demo.repository.AnotherDemoEntityRepository;
import com.example.demo.repository.ColumnConverterDemoEntityRepository;
import com.example.demo.repository.DemoEntityRepository;
import com.example.demo.repository.JPAQueryRepository;
import com.example.demo.repository.ValueTransformRepository;
import com.example.demo.repository.YetAnotherDemoEntityRepository;
import com.example.demo.service.JPAQueryService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    
    private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);
    
    @Autowired
    DemoEntityRepository repository;
    @Autowired
    AnotherDemoEntityRepository anotherRepository;
    @Autowired
    JPAQueryService jpaQueryService;
    @Autowired
    JPAQueryRepository jpaQueryRepository;
    @Autowired
    YetAnotherDemoEntityRepository yetAnotherDemoEntityRepository;
    @Autowired
    ValueTransformRepository valueTransformRepository;
    @Autowired
    ColumnConverterDemoEntityRepository columnConverterDemoEntityRepository;
    @Autowired
    StandardPBEStringEncryptor encryptor;
    
    @Test 
    public void contextLoads() {}
    
    /* This approach works */
    @Test
    public void testEncryptionWithCustomTypeDef() {
        
        DemoEntity entity = new DemoEntity("test");
        repository.save(entity);
        logger.info("TypeDef test: {}", entity);
        
        DemoEntity entity1 = repository.findByEncryptedFieldEquals("test");
        Assert.assertNotNull("Should not be null", entity1);
        
        DemoEntity entity2 = repository.findByEncryptedField("test");
        logger.info("null? {}", null == entity2);
        Assert.assertNotNull("Should not be null.", entity2);
        
        List<DemoEntity> entityList = (List<DemoEntity>) repository.findAll();
        logger.info("entities? {}", entityList.isEmpty());
        for (DemoEntity e : entityList) {
            logger.info(e.toString());
        }
    }

    /* This approach works */
    @Test
    public void testEncryptionWithEntityAttributeConverter() {

        DemoEntityTwo entity = new DemoEntityTwo("test");
        anotherRepository.save(entity);
        logger.info("Test: {}", entity);

        DemoEntityTwo entity1 = anotherRepository.findByName("test");
        logger.info("Test find by encrypted field: {}", entity1);
        Assert.assertNotNull("Should not be null.", entity1);
        
        entity1 = anotherRepository.findByUnencryptedName("test");
        logger.info("Test find by unencrypted field: {}", entity1);
        Assert.assertNotNull("Should not be null.", entity1);

        Iterable<DemoEntityTwo> entityList = anotherRepository.findAll();
        for (DemoEntityTwo e : entityList) {
            logger.info(e.toString());
        }
    }
    
    /* This approach also works */
    @Test
    public void testJPAQuery() {
        DemoEntityThree entity = new DemoEntityThree("jpaQueryTest");
        jpaQueryService.save(entity);

        DemoEntityThree entity1 = jpaQueryService.findByUnencryptedValue("jpaQueryTest");
        logger.info("JPAQuery: null? {}", null == entity1 ? true : entity1.toString());
        Assert.assertNotNull("Should not be null.", entity1);
    }
    
    /* This doesn't work, which is just as well (it's an ugly solution). */
    @Test
    public void testFieldLevelEncryption() {

        DemoEntityFour entity = new DemoEntityFour("anotherTest");
        yetAnotherDemoEntityRepository.save(entity);
        logger.info("field level entity: {}", entity.toString());
        DemoEntityFour entity1 = yetAnotherDemoEntityRepository.findByName("anotherTest");
        logger.info("find by name: {}", null == entity1 ? "null" : entity1.toString());
        Assert.assertNull("Should be null.", entity1);
        
    }
    
    /* This works, and mimics our existing repository structure */
    @Test
    public void testWrappedEncryption() {
        DemoEntityThree entity = new DemoEntityThree("testAgain");
        valueTransformRepository.testSave(entity);
        logger.info("wrapped entity: {}", entity.toString());
        DemoEntityThree entity1 = valueTransformRepository.findByEncryptedName("testAgain");
        logger.info("find by encrypted name: {}", null == entity1 ? "null" : entity1.toString());
        Assert.assertNotNull("Should not be null.", entity1);
    }
    
    /* This always works. */
    @Test
    public void testColumnTransformerEncryption() {
        DemoEntityFive entity = new DemoEntityFive("columnConverterTest");
        columnConverterDemoEntityRepository.save(entity);
        logger.info("column converter entity: {}", entity.toString());
        
        DemoEntityFive entity1 = columnConverterDemoEntityRepository.findDemoEntityByEncryptedField("columnConverterTest");
        logger.info("column converter entity: find by encrypted field {}", entity1.toString());
        Assert.assertNotNull("Should not be null.", entity1);
    }

}
