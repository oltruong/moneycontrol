package com.oltruong.moneycontrol.rule;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Olivier Truong
 */
public interface RuleRepository extends MongoRepository<Rule, Long> {
    Optional<Rule> findById(String id);


}