package com.oltruong.moneycontrol.parser;

import com.google.common.collect.Lists;
import com.oltruong.moneycontrol.model.Operation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author oltruong
 */
public class SGBankParser implements BankParser {

    static final int FIRST_ACTIVE_LINE = 3;

    @Override
    public List<Operation> parseString(String fileContent) {
        String[] lines = fileContent.split("\r\n");
        List<Operation> operationList = Lists.newArrayListWithExpectedSize(lines.length - FIRST_ACTIVE_LINE + 1);
        for (int i = FIRST_ACTIVE_LINE; i < lines.length; i++) {
            String str[] = lines[i].split(";");
            if (str.length > 3) {
                Operation operation = parseOperation(str);
                operationList.add(operation);
            }
        }
        return operationList;
    }


    private static Operation parseOperation(String[] str) {
        Operation operation = new Operation();
        LocalDateTime localDate = LocalDate.parse(str[0], DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
        operation.setCreationDate(Date.from(localDate.toInstant(ZoneOffset.UTC)));
        operation.setMonth(localDate.getMonthValue());
        operation.setYear(localDate.getYear());
        operation.setName(str[2].replace("\"", ""));
        operation.setAmount(Float.valueOf(str[3].replace(',', '.')));
        return operation;
    }
}
