package com.oltruong.moneycontrol.repository;

import com.oltruong.moneycontrol.model.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Olivier Truong
 */
public interface OperationRepository extends CrudRepository<Operation, Long> {

    List<Operation> findByYearAndMonth(int year, int month);

    List<Operation> findByYear(int year);

    List<Operation> findByCategoryEmpty();

}