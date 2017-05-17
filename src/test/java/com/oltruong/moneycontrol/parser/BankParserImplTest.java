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
public class BankParserImplTest extends BankParserTest {

    @Test
    public void testParseString() throws Exception {
        String fileContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("bou.csv").toURI())));

        BankParserImpl bankParserImpl = new BankParserImpl();

        List<Operation> operationList = bankParserImpl.parseString(fileContent);

        assertThat(operationList).hasSize(4);
        checkOperation(operationList.get(0), "SUPERMARKET", -16.8f, 2017, 03);
        checkOperation(operationList.get(1), "MARKET", -24.85f, 2016, 03);
        checkOperation(operationList.get(2), "TRANSFER", -1000f, 2016, 03);
        checkOperation(operationList.get(3), "TRANSFER", 400f, 2016, 03);
    }


}