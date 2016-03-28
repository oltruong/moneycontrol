package com.oltruong.moneycontrol.parser;

import com.oltruong.moneycontrol.model.Operation;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author oltruong
 */
public class SGBankParserTest extends BankParserTest {

    @Test
    public void testParseString() throws Exception {
        String fileContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("sg.csv").toURI())));

        SGBankParser bankParser = new SGBankParser();
        List<Operation> operationList = bankParser.parseString(fileContent);

        assertThat(operationList).hasSize(3);

        checkOperation(operationList.get(0), "DETAIL3", -90.23f, 2016, 05);
        checkOperation(operationList.get(1), "DETAIL2", -44.34f, 2015, 04);
        checkOperation(operationList.get(2), "DETAIL1", 44.34f, 2014, 03);


    }


}