package com.oltruong.moneycontrol.operation;

import com.oltruong.moneycontrol.HttpUtils;
import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;
import com.oltruong.moneycontrol.exception.ResourceNotFoundException;
import com.oltruong.moneycontrol.rule.Rule;
import com.oltruong.moneycontrol.rule.RuleRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OperationControllerTest {

    @Mock
    private OperationRepository mockOperationRepository;

    @Mock
    private RuleRepository mockRuleRepository;

    @Mock
    private BudgetAnalyzer mockBudgetAnalyzer;

    private OperationController operationController;


    @BeforeEach
    public void setup(){
        operationController = new OperationController(mockOperationRepository, mockRuleRepository, mockBudgetAnalyzer);
    }


    @Test
    public void findAll(){
        List<Operation> operationList = Collections.singletonList(new Operation());
        when(mockOperationRepository.findAll()).thenReturn(operationList);

        Iterable<Operation> operationsReturned = operationController.findAll(null, null, null);

        assertThat(operationList).isEqualTo(operationsReturned);
        verify(mockOperationRepository).findAll();
    }

    @Test
    public void findAll_year(){
        List<Operation> operationList = Collections.singletonList(new Operation());
        when(mockOperationRepository.findByYear(2016)).thenReturn(operationList);

        Iterable<Operation> operationsReturned = operationController.findAll(2016, null, null);

        assertThat(operationList).isEqualTo(operationsReturned);
        verify(mockOperationRepository).findByYear(eq(2016));
    }

    @Test
    public void findAll_monthyear(){
        List<Operation> operationList = Collections.singletonList(new Operation());
        when(mockOperationRepository.findByYearAndMonth(2015, 9)).thenReturn(operationList);

        Iterable<Operation> operationsReturned = operationController.findAll(2015, 9, null);

        assertThat(operationList).isEqualTo(operationsReturned);
        verify(mockOperationRepository).findByYearAndMonth(eq(2015), eq(9));
    }

    @Test
    public void findAll_empty(){
        List<Operation> operationList = Collections.singletonList(new Operation());
        when(mockOperationRepository.findByCategoryNull()).thenReturn(operationList);

        Iterable<Operation> operationsReturned = operationController.findAll(null, null, "empty");

        assertThat(operationList).isEqualTo(operationsReturned);
        verify(mockOperationRepository).findByCategoryNull();
    }

    @Test
    public void editOperation(){

        when(mockOperationRepository.findById(anyString())).thenReturn(Optional.of(new Operation()));

        Operation operation = new Operation();
        operation.setName("name");
        operationController.editOperation(operation, "id");

        OperationAsserter.assertThat(operation).hasId("id");
        verify(mockOperationRepository).save(eq(operation));
    }

    @Test
    public void editOperation_notfound() {
        when(mockOperationRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                operationController.editOperation(new Operation(), "id"));
    }

    @Test
    public void createOperation(){
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
    public void get() {
        Operation operation = getOperation();
        when(mockOperationRepository.findById("id")).thenReturn(Optional.of(operation));

        Operation operationFound = operationController.get("id");

        assertThat(operation).isEqualTo(operationFound);
        verify(mockOperationRepository).findById(eq("id"));
    }


    @Test
    public void get_notfound() {
        when(mockOperationRepository.findById("id")).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                operationController.get("id"));
    }

    @Test
    public void delete() {
        Operation operation = getOperation();

        when(mockOperationRepository.findById(anyString())).thenReturn(Optional.of(operation));
        operationController.delete("id");

        verify(mockOperationRepository).delete(eq(operation));
    }

    @Test
    public void delete_notfound() {
        when(mockOperationRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                operationController.delete("id"));
    }

    @Test
    public void refresh() {


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