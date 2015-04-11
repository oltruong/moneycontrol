package com.oltruong.moneycontrol.controller;

import com.google.common.collect.Lists;
import com.oltruong.moneycontrol.model.Operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author oltruong
 */
public class BankParser {

    static final int FIRST_ACTIVE_LINE = 8;

    public static List<Operation> parseString(String fileContent) {
        String[] lines = fileContent.split("\r\n");
        List<Operation> operationList = Lists.newArrayListWithExpectedSize(lines.length - 7);
        for (int i = FIRST_ACTIVE_LINE; i < lines.length; i++) {
            String str[] = lines[i].split(";");
            if (str.length > 3) {
                Operation operation = parseOperation(str);
                operationList.add(operation);
            }
        }
        return operationList;
    }


    public static List<Operation> parseFile(File file) throws IOException {
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        skipFirstLines(bufferedFile);
        List<Operation> operationList = Lists.newArrayList();
        String line;
        while ((line = bufferedFile.readLine()) != null) {
            String str[] = line.split(";");
            if (str.length > 3) {
                Operation operation = parseOperation(str);
                operationList.add(operation);
            }

        }
        return operationList;
    }

    private static void skipFirstLines(BufferedReader file) throws IOException {
        boolean done = false;
        String line;
        while (!done && (line = file.readLine()) != null) {
            String str[] = line.split(";");
            done = str.length > 3 && str[0].length() == 10;
        }
    }

    private static Operation parseOperation(String[] str) {
        Operation operation = new Operation();
        LocalDateTime localDate = LocalDate.parse(str[0], DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
        operation.setCreationDate(Date.from(localDate.toInstant(ZoneOffset.UTC)));
        operation.setMonth(localDate.getMonthValue());
        operation.setYear(localDate.getYear());
        operation.setName(str[1].replace("\"", ""));
        operation.setAmount(Float.valueOf(str[2].replace(',', '.')));
        return operation;
    }
}
