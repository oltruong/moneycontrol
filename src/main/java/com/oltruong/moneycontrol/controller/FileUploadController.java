package com.oltruong.moneycontrol.controller;

import com.oltruong.moneycontrol.exception.BadRequestException;
import com.oltruong.moneycontrol.model.Operation;
import com.oltruong.moneycontrol.model.Rule;
import com.oltruong.moneycontrol.parser.BankFileAnalyzer;
import com.oltruong.moneycontrol.repository.OperationRepository;
import com.oltruong.moneycontrol.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Truong
 */
@RestController
public class FileUploadController {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private RuleRepository ruleRepository;


    @RequestMapping(value = "/rest/bankfileupload", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void upload(@RequestBody String fileContent) {
        if (fileContent == null) {
            throw new BadRequestException();
        } else {
            List<Operation> operationList = BankFileAnalyzer.parseString(fileContent);
            List<String> existingOperationList = buildOperationList();

            Iterable<Rule> ruleList = ruleRepository.findAll();

            for (Operation operation : operationList) {
                if (operationMustBeAdded(operation, existingOperationList)) {
                    operation = BudgetAnalyzer.analyzeOperation(operation, ruleList);
                    operationRepository.save(operation);
                }
            }

        }
    }


    private List<String> buildOperationList() {

        Iterable<Operation> allOperations = operationRepository.findAll();
        List<String> operationList = new ArrayList<>();
        allOperations.forEach(operation -> operationList.add(generateKey(operation)));

        return operationList;

    }

    private boolean operationMustBeAdded(Operation operation, List<String> existingOperationList) {
        return operationDoesNotExist(operation, existingOperationList) && operationIsNotTooOld(operation);
    }

    private boolean operationDoesNotExist(Operation operation, List<String> existingOperationList) {

        return !existingOperationList.contains(generateKey(operation));

    }

    private boolean operationIsNotTooOld(Operation operation) {
        return operation.getCreationDate().toInstant().atOffset(ZoneOffset.UTC).getYear() >= 2015;
    }

    private String generateKey(Operation operation) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(operation.getCreationDate()) + operation.getName();
    }
}
