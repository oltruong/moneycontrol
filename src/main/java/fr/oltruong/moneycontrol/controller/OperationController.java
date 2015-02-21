package fr.oltruong.moneycontrol.controller;

import fr.oltruong.moneycontrol.model.Operation;
import fr.oltruong.moneycontrol.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Olivier Truong
 */
@RestController
public class OperationController {


    @Autowired
    private OperationRepository operationRepository;

    @RequestMapping("/rest/operation/period/{year}/{month}")
    Iterable<Operation> findByYearAndMonth(@PathVariable int year, @PathVariable int month) {
        return operationRepository.findByYearAndMonth(year, month);
    }

    @RequestMapping("/rest/operation/period/{year}")
    Iterable<Operation> findByYear(@PathVariable int year) {
        return operationRepository.findByYear(year);
    }

    @RequestMapping("/rest/operation")
    Iterable<Operation> findAll() {
        return operationRepository.findAll();
    }

    @RequestMapping(value = "/rest/operation/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void editOperation(@RequestBody Operation operation, @PathVariable Long id) {
        operation.setId(id);
        operationRepository.save(operation);
    }
}