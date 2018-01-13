package com.example.demo;

import com.example.demo.entity.AnotherDemoEntity;
import com.example.demo.entity.DemoEntity;
import com.example.demo.entity.YetAnotherDemoEntity;
import com.example.demo.repository.AnotherDemoEntityRepository;
import com.example.demo.repository.DemoEntityRepository;
import com.example.demo.repository.JPAQueryRepository;
import com.example.demo.repository.YetAnotherDemoEntityRepository;
import com.example.demo.service.JPAQueryService;
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
    
    @Test 
    public void contextLoads() {}
    
    /* None of the following approaches work with JPA & encryption as far as I can tell */
    
    @Test
    public void testDemoEntity() {
        
        DemoEntity entity = new DemoEntity("test");
        repository.save(entity);
        logger.info("Test: {}", entity);
        
        DemoEntity entity1 = new DemoEntity();
        entity1.setEncryptedField("test1");
        repository.save(entity1);
        logger.info("Test1: {}", entity1);
        
        DemoEntity entity2 = repository.findDemoEntityByEncryptedField("test");
        logger.info("null? {}", null == entity2);
        Assert.assertNull(entity2);
        
        List<DemoEntity> entityList = (List<DemoEntity>) repository.findAll();
        logger.info("entities? {}", entityList.isEmpty());
        for (DemoEntity e : entityList) {
            logger.info(e.toString());
        }
    }

    @Test
    public void testAnotherDemoEntity() {

        AnotherDemoEntity entity = new AnotherDemoEntity("test");
        anotherRepository.save(entity);
        logger.info("Test: {}", entity);

        AnotherDemoEntity entity1 = anotherRepository.findByName("test");
        logger.info("Test find by encrypted field: {}", entity1);
        Assert.assertNull(entity1);
        entity1 = anotherRepository.findByUnencryptedName("test");
        logger.info("Test find by unencrypted field: {}");
        Assert.assertNotNull(entity1);

        Iterable<AnotherDemoEntity> entityList = anotherRepository.findAll();
        logger.info("entities? {}", entityList.iterator().hasNext());
        for (AnotherDemoEntity e : entityList) {
            logger.info(e.toString());
        }
    }
    
    @Test
    public void testJPAQuery() {
        DemoEntity entity = new DemoEntity("jpaQueryTest");
        repository.save(entity);
        
        DemoEntity entity1 = jpaQueryService.findByUnencryptedValue("jpaQueryTest");
        logger.info("JPAQuery: null? {}", null == entity1 ? true : entity1.toString());
        Assert.assertNull(entity1);
    }
    
    @Test
    public void testFieldLevelEncryption() {

        YetAnotherDemoEntity entity = new YetAnotherDemoEntity("anotherTest");
        yetAnotherDemoEntityRepository.save(entity);
        logger.info("field level entity: {}", entity.toString());
        YetAnotherDemoEntity entity1 = yetAnotherDemoEntityRepository.findByName("anotherTest");
        logger.info("find by name: {}", null == entity1 ? "null" : entity1.toString());
        Assert.assertNull(entity1);
        
        
    }

}
