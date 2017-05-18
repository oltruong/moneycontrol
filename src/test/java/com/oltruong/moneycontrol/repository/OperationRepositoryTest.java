package com.oltruong.moneycontrol.repository;

import com.oltruong.moneycontrol.model.Operation;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
@SpringBootConfiguration
public class OperationRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OperationRepository operationRepository;

    @After
    public void cleanup(){
        operationRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        assertThat(operationRepository.findAll()).isEmpty();

        Operation operation = new Operation();
        operation.setName("Name");

        mongoTemplate.save(operation);

        assertThat(operationRepository.findAll()).hasSize(1);

        assertThat(operationRepository.findAll().get(0)).isEqualToComparingFieldByField(operation);

        operationRepository.delete(operation);
        assertThat(operationRepository.findAll()).isEmpty();
    }

    @Test
    public void findByCategoryNull() throws Exception {


        Operation operation = new Operation();
        operation.setName("Name");

        mongoTemplate.save(operation);

        assertThat(operationRepository.findByCategoryNull()).hasSize(1);

        assertThat(operationRepository.findByCategoryNull().get(0)).isEqualToComparingFieldByField(operation);

        operation.setCategory("category");
        mongoTemplate.save(operation);

        assertThat(operationRepository.findByCategoryNull()).isEmpty();

    }

}