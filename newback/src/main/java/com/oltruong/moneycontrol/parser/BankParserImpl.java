package com.oltruong.moneycontrol.parser;

import com.oltruong.moneycontrol.operation.Operation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Olivier Truong
 */
@ApplicationScoped
public class BankParserImpl implements BankParser {
    private static final int FIRST_ACTIVE_LINE = 1;

    @Override
    public List<Operation> parseString(String fileContent) {
        String[] lines = fileContent.split("\n");
        List<Operation> operationList = new ArrayList<>(lines.length - -FIRST_ACTIVE_LINE + 1);
        for (int i = FIRST_ACTIVE_LINE; i < lines.length; i++) {
            String[] str = lines[i].split(";");
            if (str.length > 4) {
                Operation operation = parseOperation(str);
                operationList.add(operation);
            }
        }
        return operationList;
    }

    private static Operation parseOperation(String[] str) {
        Operation operation = new Operation();
        LocalDateTime localDateTime = LocalDate.parse(str[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        operation.creationDate = LocalDate.from(localDateTime);
        operation.month = localDateTime.getMonthValue();
        operation.year = localDateTime.getYear();
        operation.name = str[2].replace("\"", "").trim();
        operation.amount = Float.valueOf(str[5].replace(',', '.').replace("\"", "").replace(" ", ""));
        return operation;
    }
}
