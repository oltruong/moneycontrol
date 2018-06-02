package com.oltruong.moneycontrol.operation;

import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;
import com.oltruong.moneycontrol.exception.ResourceNotFoundException;
import com.oltruong.moneycontrol.rule.Rule;
import com.oltruong.moneycontrol.rule.RuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Olivier Truong
 */
@RestController
public class OperationController {

    private final OperationRepository operationRepository;

    private final RuleRepository ruleRepository;

    private final BudgetAnalyzer budgetAnalyzer;


    @Autowired
    public OperationController(OperationRepository operationRepository, RuleRepository ruleRepository, BudgetAnalyzer budgetAnalyzer) {
        this.operationRepository = operationRepository;
        this.ruleRepository = ruleRepository;
        this.budgetAnalyzer = budgetAnalyzer;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/rest/operations", method = RequestMethod.GET)
    Iterable<Operation> findAll(@RequestParam(value = "year", required = false) Integer year, @RequestParam(value = "month", required = false) Integer month, @RequestParam(value = "category", required = false) String category) {

        if ("empty".equals(category)) {
            return operationRepository.findByCategoryNull();
        } else if (year != null && month != null) {
            return operationRepository.findByYearAndMonth(year, month);
        } else if (year != null) {
            return operationRepository.findByYear(year);
        } else {
            return operationRepository.findAll();
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/rest/operations/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void editOperation(@RequestBody Operation operation, @PathVariable String id) {
        getOperationOrThrowException(id);
        operation.setId(id);
        operationRepository.save(operation);
    }

    @RequestMapping(value = "/rest/operations", method = RequestMethod.POST)
    ResponseEntity<Operation> createOperation(@RequestBody Operation operation) {
        operation.setId(null);

        Operation operationSaved = operationRepository.save(operation);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(operationSaved.getId()).toUri());

        return new ResponseEntity<>(operationSaved, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rest/operations/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    Operation get(@PathVariable String id) {
        return getOperationOrThrowException(id);
    }

    private Operation getOperationOrThrowException(String id) {
        return operationRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "/rest/operations/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        Operation operation = getOperationOrThrowException(id);
        operationRepository.delete(operation);
    }

    @RequestMapping(value = "/rest/operations/refresh", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void refresh() {
        Iterable<Rule> ruleList = ruleRepository.findAll();

        operationRepository.findByCategoryNull().forEach(operation ->
                operationRepository.save(budgetAnalyzer.analyzeOperation(operation, ruleList))
        );

    }

}