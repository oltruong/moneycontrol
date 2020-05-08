package com.oltruong.moneycontrol.fileupload;

import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;
import com.oltruong.moneycontrol.operation.Operation;
import com.oltruong.moneycontrol.parser.BankParser;
import com.oltruong.moneycontrol.rule.Rule;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * @author Olivier Truong
 */
@Path("/rest/bankfileupload")
public class FileUploadResource {

    @Inject
    private BankParser bankParser;

    @Inject
    private BudgetAnalyzer budgetAnalyzer;


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void upload(String fileContent) throws IOException {


//        String fileContent = IOUtils.toString(inputStream, StandardCharsets.ISO_8859_1.name());
//        String fileContent = checkInput(multipartFile);

        System.out.println(fileContent);
        List<Operation> operationList = bankParser.parseString(fileContent);
        List<String> existingOperationKeyList = buildOperationKeyList();
//
        Iterable<Rule> ruleList = Rule.listAll();

        operationList.stream()
                     .filter(operation -> operationMustBeAdded(operation, existingOperationKeyList))
                     .forEach(operation -> addOperation(ruleList, operation));
    }

//    private String checkInput(MultipartFile multipartFile) throws IOException {
//        if (multipartFile == null) {
//            throw new BadRequestException();
//        }
//        String fileContent = new String(multipartFile.getBytes(), "ISO-8859-15");
//        if (fileContent.isEmpty()) {
//            throw new BadRequestException();
//        }
//        return fileContent;
//    }

    private void addOperation(Iterable<Rule> ruleList, Operation operation) {
        budgetAnalyzer.analyzeOperation(operation, ruleList)
                      .persist();
    }


    private List<String> buildOperationKeyList() {
        List<Operation> operations = Operation.listAll();
        return operations.stream().map(this::generateKey).collect(Collectors.toList());

    }

    private boolean operationMustBeAdded(Operation operation, List<String> existingOperationList) {
        return operationDoesNotExist(operation, existingOperationList) && operationIsNotTooOld(operation);
    }

    private boolean operationDoesNotExist(Operation operation, List<String> existingOperationList) {
        return !existingOperationList.contains(generateKey(operation));

    }

    private boolean operationIsNotTooOld(Operation operation) {
        return operation.creationDate.getYear() >= 2015;
    }

    public String generateKey(Operation operation) {
        return operation.creationDate.toString() + operation.name;
    }
}
