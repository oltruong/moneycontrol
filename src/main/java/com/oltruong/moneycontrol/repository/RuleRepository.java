package com.oltruong.moneycontrol.repository;

import com.oltruong.moneycontrol.model.Operation;
import com.oltruong.moneycontrol.model.Rule;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Olivier Truong
 */
public interface RuleRepository extends MongoRepository<Rule, Long> {
    Optional<Rule> findById(String id);


}