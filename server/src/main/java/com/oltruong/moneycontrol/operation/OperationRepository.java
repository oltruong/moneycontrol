package com.oltruong.moneycontrol.operation;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Olivier Truong
 */
public interface OperationRepository extends MongoRepository<Operation, Long> {

    List<Operation> findByYearAndMonth(int year, int month);

    List<Operation> findByYear(int year);

    List<Operation> findByCategoryNull();

    Optional<Operation> findById(String id);

}