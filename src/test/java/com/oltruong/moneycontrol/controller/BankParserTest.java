package com.oltruong.moneycontrol.controller;

import com.oltruong.moneycontrol.model.Operation;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author oltruong
 */
public class BankParserTest {

    @Test
    public void testParseString() throws Exception {
        String fileContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("lbp.csv").toURI())));

        List<Operation> operationList = BankParser.parseString(fileContent);

        assertThat(operationList).hasSize(3);

        checkOperation(operationList.get(0), "TEST1", 19.1f, 2015, 11);
        checkOperation(operationList.get(1), "TEST2", 100f, 2014, 01);
        checkOperation(operationList.get(2), "TEST3", 15.1f, 2013, 02);


    }

    private void checkOperation(Operation operation, String name, float amount, int year, int month) {
        assertThat(operation.getName()).isEqualTo(name);
        assertThat(operation.getAmount()).isEqualTo(amount);
        assertThat(operation.getYear()).isEqualTo(year);
        assertThat(operation.getMonth()).isEqualTo(month);
    }
}