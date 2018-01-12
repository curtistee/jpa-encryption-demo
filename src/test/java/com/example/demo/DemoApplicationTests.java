package com.example.demo;

import com.example.demo.entity.AnotherDemoEntity;
import com.example.demo.entity.DemoEntity;
import com.example.demo.repository.AnotherDemoEntityRepository;
import com.example.demo.repository.DemoEntityRepository;
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
    
    @Test 
    public void contextLoads() { 
        
    }
    
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

        AnotherDemoEntity entity2 = anotherRepository.findAnotherDemoEntityByName("test");
        logger.info("Test find by encrypted field: {}", entity2);

        AnotherDemoEntity entity3 = anotherRepository.findOne(entity.getId());
        logger.info("Test find by id: {}", entity3);

        AnotherDemoEntity entity1 = new AnotherDemoEntity();
        entity1.setName("test1");
        anotherRepository.save(entity1);
        logger.info("Test1: {}", entity1);

        Iterable<AnotherDemoEntity> entityList = anotherRepository.findAll();
        logger.info("entities? {}", entityList.iterator().hasNext());
        for (AnotherDemoEntity e : entityList) {
            logger.info(e.toString());
        }
    }

}
