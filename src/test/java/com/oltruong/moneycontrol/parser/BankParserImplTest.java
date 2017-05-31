package com.oltruong.moneycontrol.parser;

import com.oltruong.moneycontrol.operation.Operation;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Olivier Truong
 */
public class BankParserImplTest {

    @Test
    public void testParseString() throws Exception {
        String fileContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("bank.csv").toURI())));

        BankParserImpl bankParserImpl = new BankParserImpl();

        List<Operation> operationList = bankParserImpl.parseString(fileContent);

        assertThat(operationList).hasSize(4);
        checkOperation(operationList.get(0), "SUPERMARKET", -16.8f, 2017, 03);
        checkOperation(operationList.get(1), "MARKET", -24.85f, 2016, 03);
        checkOperation(operationList.get(2), "TRANSFER", -1000f, 2016, 03);
        checkOperation(operationList.get(3), "TRANSFER", 400f, 2016, 03);
    }

    private void checkOperation(Operation operation, String name, float amount, int year, int month) {
        assertThat(operation.getName()).isEqualTo(name);
        assertThat(operation.getAmount()).isEqualTo(amount);
        assertThat(operation.getYear()).isEqualTo(year);
        assertThat(operation.getMonth()).isEqualTo(month);
    }


}