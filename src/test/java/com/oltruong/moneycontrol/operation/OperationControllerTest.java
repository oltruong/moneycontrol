package com.oltruong.moneycontrol.operation;

import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;
import com.oltruong.moneycontrol.rule.Rule;
import com.oltruong.moneycontrol.rule.RuleRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperationControllerTest {

    @Mock
    private OperationRepository mockOperationRepository;

    @Mock
    private RuleRepository mockRuleRepository;

    @Mock
    private BudgetAnalyzer mockBudgetAnalyzer;

    private OperationController operationController;


    @Before
    public void setup() throws Exception {
        operationController = new OperationController(mockOperationRepository, mockRuleRepository, mockBudgetAnalyzer);
    }


    @Test
    public void findAll() throws Exception {
        List<Operation> operationList = Collections.singletonList(new Operation());
        when(mockOperationRepository.findAll()).thenReturn(operationList);

        Iterable<Operation> operationsReturned = operationController.findAll(null, null, null);

        assertThat(operationList).isEqualTo(operationsReturned);
        verify(mockOperationRepository).findAll();
    }

    @Test
    public void findAll_year() throws Exception {
        List<Operation> operationList = Collections.singletonList(new Operation());
        when(mockOperationRepository.findByYear(2016)).thenReturn(operationList);

        Iterable<Operation> operationsReturned = operationController.findAll(2016, null, null);

        assertThat(operationList).isEqualTo(operationsReturned);
        verify(mockOperationRepository).findByYear(eq(2016));
    }

    @Test
    public void findAll_monthyear() throws Exception {
        List<Operation> operationList = Collections.singletonList(new Operation());
        when(mockOperationRepository.findByYearAndMonth(2015, 9)).thenReturn(operationList);

        Iterable<Operation> operationsReturned = operationController.findAll(2015, 9, null);

        assertThat(operationList).isEqualTo(operationsReturned);
        verify(mockOperationRepository).findByYearAndMonth(eq(2015), eq(9));
    }

    @Test
    public void findAll_empty() throws Exception {
        List<Operation> operationList = Collections.singletonList(new Operation());
        when(mockOperationRepository.findByCategoryNull()).thenReturn(operationList);

        Iterable<Operation> operationsReturned = operationController.findAll(null, null, "empty");

        assertThat(operationList).isEqualTo(operationsReturned);
        verify(mockOperationRepository).findByCategoryNull();
    }

    @Test
    public void editOperation() throws Exception {
    }

    @Test
    public void createOperation() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void refresh() throws Exception {


        List<Rule> ruleList = Collections.singletonList(new Rule());
        when(mockRuleRepository.findAll()).thenReturn(ruleList);


        Operation blankOperation = new Operation();
        blankOperation.setName("NAME");
        when(mockOperationRepository.findByCategoryNull()).thenReturn(Collections.singletonList(blankOperation));

        Operation analyzedOperation = new Operation();
        analyzedOperation.setName("NAME");
        analyzedOperation.setCategory("ANALYZED");

        when(mockBudgetAnalyzer.analyzeOperation(blankOperation, ruleList)).thenReturn(analyzedOperation);

        operationController.refresh();


        verify(mockRuleRepository).findAll();
        verify(mockOperationRepository).findByCategoryNull();
        verify(mockBudgetAnalyzer).analyzeOperation(eq(blankOperation), eq(ruleList));
        verify(mockOperationRepository).save(eq(analyzedOperation));
    }

}