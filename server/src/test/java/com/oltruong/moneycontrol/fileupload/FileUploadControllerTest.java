package com.oltruong.moneycontrol.fileupload;

import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;
import com.oltruong.moneycontrol.exception.BadRequestException;
import com.oltruong.moneycontrol.operation.Operation;
import com.oltruong.moneycontrol.operation.OperationRepository;
import com.oltruong.moneycontrol.parser.BankParser;
import com.oltruong.moneycontrol.rule.Rule;
import com.oltruong.moneycontrol.rule.RuleRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FileUploadControllerTest {


    @Mock
    private OperationRepository mockOperationRepository;

    @Mock
    private RuleRepository mockRuleRepository;

    @Mock
    private BankParser mockBankParser;

    @Mock
    private BudgetAnalyzer mockBudgetAnalyzer;

    private FileUploadController fileUploadController;

    @Before
    public void setup() {
        fileUploadController = new FileUploadController(mockOperationRepository, mockRuleRepository, mockBankParser, mockBudgetAnalyzer);
    }

    @Test
    public void upload() throws Exception {

        Date now = new Date();

        Operation operation = new Operation();
        operation.setName("NAME");
        operation.setCreationDate(now);

        Operation existingOperation = new Operation();
        existingOperation.setName("NAME");
        existingOperation.setCreationDate(new Date(1L));

        Operation oldOperation = new Operation();
        oldOperation.setName("OLD");
        oldOperation.setCreationDate(new Date(1L));

        when(mockBankParser.parseString("fileContent")).thenReturn(Arrays.asList(operation, existingOperation, oldOperation));
        when(mockOperationRepository.findAll()).thenReturn(Collections.singletonList(existingOperation));

        Rule rule = new Rule();
        rule.setNameCondition("NAME CONDITION");
        List<Rule> ruleList = Collections.singletonList(rule);
        when(mockRuleRepository.findAll()).thenReturn(ruleList);


        Operation transformedOperation = new Operation();
        transformedOperation.setName("NAME");
        transformedOperation.setCreationDate(now);

        when(mockBudgetAnalyzer.analyzeOperation(operation,ruleList)).thenReturn(transformedOperation);


        MockMultipartFile mockMultipartFile =  new MockMultipartFile("fileContent","fileContent".getBytes());
        fileUploadController.upload(mockMultipartFile);

        verify(mockOperationRepository).save(eq(transformedOperation));

    }

    @Test(expected = BadRequestException.class)
    public void uploadBadRequest() throws Exception {
        fileUploadController.upload(null);
    }

}