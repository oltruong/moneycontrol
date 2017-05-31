package com.oltruong.moneycontrol.fileupload;

import com.oltruong.moneycontrol.exception.BadRequestException;
import com.oltruong.moneycontrol.operation.OperationRepository;
import com.oltruong.moneycontrol.rule.RuleRepository;
import com.oltruong.moneycontrol.parser.BankParser;
import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


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
    }

    @Test(expected = BadRequestException.class)
    public void uploadBadRequest() throws Exception {
        fileUploadController.upload(null);
    }

}