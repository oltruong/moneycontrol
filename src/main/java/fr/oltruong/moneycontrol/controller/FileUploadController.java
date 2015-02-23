package fr.oltruong.moneycontrol.controller;

import fr.oltruong.moneycontrol.exception.BadRequestException;
import fr.oltruong.moneycontrol.model.Operation;
import fr.oltruong.moneycontrol.model.Rule;
import fr.oltruong.moneycontrol.repository.OperationRepository;
import fr.oltruong.moneycontrol.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/rest/bankfileupload")
    public void upload(@RequestBody String fileContent) {

        if (fileContent == null) {
            throw new BadRequestException();
        } else {
            List<Operation> operationList = BankParser.parseString(fileContent);
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
        allOperations.forEach(operation -> operationList.add(operation.getCreationDate() + operation.getName()));

        return operationList;

    }

    private static boolean operationMustBeAdded(Operation operation, List<String> existingOperationList) {
        return operationDoesNotExist(operation, existingOperationList) && operationIsNotTooOld(operation);
    }

    private static boolean operationDoesNotExist(Operation operation, List<String> existingOperationList) {

        String operationKey = operation.getCreationDate() + operation.getName();
        return !existingOperationList.contains(operationKey);

    }

    private static boolean operationIsNotTooOld(Operation operation) {
        return operation.getCreationDate().toInstant().atOffset(ZoneOffset.UTC).getYear() >= 2015;
    }
}
