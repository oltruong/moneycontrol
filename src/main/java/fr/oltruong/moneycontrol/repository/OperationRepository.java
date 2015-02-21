package fr.oltruong.moneycontrol.repository;

import fr.oltruong.moneycontrol.model.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Olivier Truong
 */
public interface OperationRepository extends CrudRepository<Operation, Long> {

    List<Operation> findByYearAndMonth(int year, int month);

    List<Operation> findByYear(int year);
}