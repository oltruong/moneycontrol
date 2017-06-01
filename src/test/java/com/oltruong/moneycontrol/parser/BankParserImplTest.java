package com.oltruong.moneycontrol.parser;

import com.oltruong.moneycontrol.operation.Operation;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.oltruong.moneycontrol.operation.OperationAsserter.assertThat;


/**
 * @author Olivier Truong
 */
public class BankParserImplTest {

    @Test
    public void parseString() throws Exception {
        String fileContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("bank.csv").toURI())));

        BankParserImpl bankParserImpl = new BankParserImpl();

        List<Operation> operationList = bankParserImpl.parseString(fileContent);

        Assertions.assertThat(operationList).hasSize(4);

        assertThat(operationList.get(0)).hasName("SUPERMARKET")
                                        .hasAmount(-16.8f)
                                        .hasYear(2017)
                                        .hasMonth(3);

        assertThat(operationList.get(1)).hasName("MARKET")
                                        .hasAmount(-24.85f)
                                        .hasYear(2016)
                                        .hasMonth(3);

        assertThat(operationList.get(2)).hasName("TRANSFER")
                                        .hasAmount(-1000f)
                                        .hasYear(2016)
                                        .hasMonth(3);

        assertThat(operationList.get(3)).hasName("TRANSFER")
                                        .hasAmount(400f)
                                        .hasYear(2016)
                                        .hasMonth(3);
    }


}