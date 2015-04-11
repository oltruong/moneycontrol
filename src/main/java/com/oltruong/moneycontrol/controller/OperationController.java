package com.oltruong.moneycontrol.controller;

import com.oltruong.moneycontrol.exception.ResourceNotFoundException;
import com.oltruong.moneycontrol.model.Operation;
import com.oltruong.moneycontrol.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @RequestMapping("/rest/operation/period/unclassified")
    Iterable<Operation> findUnClassified() {
        return operationRepository.findByCategoryNotEmpty();
    }

    @RequestMapping("/rest/operation/period/{year}")
    Iterable<Operation> findByYear(@PathVariable int year) {
        return operationRepository.findByYear(year);
    }

    @RequestMapping(value = "/rest/operation", method = RequestMethod.GET)
    Iterable<Operation> findAll() {
        return operationRepository.findAll();
    }

    @RequestMapping(value = "/rest/operation/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void editOperation(@RequestBody Operation operation, @PathVariable Long id) {

        if (operationRepository.findOne(id) == null) {
            throw new ResourceNotFoundException();
        }
        operation.setId(id);
        operationRepository.save(operation);

    }

    @RequestMapping(value = "/rest/operation", method = RequestMethod.POST)
    ResponseEntity<?> createOperation(@RequestBody Operation operation) {
        operation.setId(null);

        Operation operationSaved = operationRepository.save(operation);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(operationSaved.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rest/operation/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    Operation get(@PathVariable Long id) {
        Operation operation = operationRepository.findOne(id);
        if (operation == null) {
            throw new ResourceNotFoundException();
        }
        return operation;
    }

    @RequestMapping(value = "/rest/operation/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        Operation operation = operationRepository.findOne(id);
        if (operation == null) {
            throw new ResourceNotFoundException();
        }
        operationRepository.delete(id);
    }
}