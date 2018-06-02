package com.oltruong.moneycontrol.fileupload;

import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;
import com.oltruong.moneycontrol.exception.BadRequestException;
import com.oltruong.moneycontrol.operation.Operation;
import com.oltruong.moneycontrol.operation.OperationRepository;
import com.oltruong.moneycontrol.parser.BankParser;
import com.oltruong.moneycontrol.rule.Rule;
import com.oltruong.moneycontrol.rule.RuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Olivier Truong
 */
@RestController
public class FileUploadController {

    private final OperationRepository operationRepository;

    private final RuleRepository ruleRepository;

    private final BankParser bankParser;

    private final BudgetAnalyzer budgetAnalyzer;


    @Autowired
    public FileUploadController(OperationRepository operationRepository, RuleRepository ruleRepository, BankParser bankParser, BudgetAnalyzer budgetAnalyzer) {
        this.operationRepository = operationRepository;
        this.ruleRepository = ruleRepository;
        this.bankParser = bankParser;
        this.budgetAnalyzer = budgetAnalyzer;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/rest/bankfileupload", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void upload(@RequestParam("operations") MultipartFile multipartFile) throws IOException {

        String fileContent = new String(multipartFile.getBytes(), "ISO-8859-15");
        if (fileContent.isEmpty()) {
            throw new BadRequestException();
        } else {
            List<Operation> operationList = bankParser.parseString(fileContent);
            List<String> existingOperationKeyList = buildOperationKeyList();

            Iterable<Rule> ruleList = ruleRepository.findAll();

            operationList.stream()
                         .filter(operation -> operationMustBeAdded(operation, existingOperationKeyList))
                         .forEach(operation -> addOperation(ruleList, operation));
        }
    }

    private void addOperation(Iterable<Rule> ruleList, Operation operation) {
        Operation analyzeOperation = budgetAnalyzer.analyzeOperation(operation, ruleList);
        operationRepository.save(analyzeOperation);
    }


    private List<String> buildOperationKeyList() {
        return operationRepository.findAll().stream().map(this::generateKey).collect(Collectors.toList());

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
