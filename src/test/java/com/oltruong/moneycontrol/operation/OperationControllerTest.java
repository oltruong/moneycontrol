package com.oltruong.moneycontrol.operation;

import com.oltruong.moneycontrol.HttpUtils;
import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;
import com.oltruong.moneycontrol.exception.ResourceNotFoundException;
import com.oltruong.moneycontrol.rule.Rule;
import com.oltruong.moneycontrol.rule.RuleRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
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

        when(mockOperationRepository.findById(anyString())).thenReturn(Optional.of(new Operation()));

        Operation operation = new Operation();
        operation.setName("name");
        operationController.editOperation(operation, "id");

        OperationAsserter.assertThat(operation).hasId("id");
        verify(mockOperationRepository).save(eq(operation));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void editOperation_notfound() throws Exception {
        when(mockOperationRepository.findById(anyString())).thenReturn(Optional.empty());
        operationController.editOperation(new Operation(), "id");
    }

    @Test
    public void createOperation() throws Exception {
        Operation operation = getOperation();

        Operation operationCreated = getOperation();
        operationCreated.setId("id");

        when(mockOperationRepository.save(operation)).thenReturn(operationCreated);

        HttpUtils.initRequestAttributes();


        ResponseEntity<Operation> responseEntity = operationController.createOperation(operation);

        assertThat(responseEntity.getBody()).isEqualTo(operationCreated);
        verify(mockOperationRepository).save(eq(operation));
    }



    @Test
    public void get() throws Exception {
        Operation operation = getOperation();
        when(mockOperationRepository.findById("id")).thenReturn(Optional.of(operation));

        Operation operationFound = operationController.get("id");

        assertThat(operation).isEqualTo(operationFound);
        verify(mockOperationRepository).findById(eq("id"));
    }


    @Test(expected = ResourceNotFoundException.class)
    public void get_notfound() throws Exception {
        when(mockOperationRepository.findById("id")).thenReturn(Optional.empty());
        operationController.get("id");
    }

    @Test
    public void delete() throws Exception {
        Operation operation = getOperation();

        when(mockOperationRepository.findById(anyString())).thenReturn(Optional.of(operation));
        operationController.delete("id");

        verify(mockOperationRepository).delete(eq(operation));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_notfound() throws Exception {
        when(mockOperationRepository.findById(anyString())).thenReturn(Optional.empty());
        operationController.delete("id");
    }

    @Test
    public void refresh() throws Exception {


        List<Rule> ruleList = Collections.singletonList(new Rule());
        when(mockRuleRepository.findAll()).thenReturn(ruleList);


        Operation blankOperation = getOperation();
        when(mockOperationRepository.findByCategoryNull()).thenReturn(Collections.singletonList(blankOperation));

        Operation analyzedOperation = getOperation();
        analyzedOperation.setCategory("ANALYZED");

        when(mockBudgetAnalyzer.analyzeOperation(blankOperation, ruleList)).thenReturn(analyzedOperation);

        operationController.refresh();


        verify(mockRuleRepository).findAll();
        verify(mockOperationRepository).findByCategoryNull();
        verify(mockBudgetAnalyzer).analyzeOperation(eq(blankOperation), eq(ruleList));
        verify(mockOperationRepository).save(eq(analyzedOperation));
    }

    private Operation getOperation() {
        Operation operation = new Operation();
        operation.setName("NAME");
        return operation;
    }


}