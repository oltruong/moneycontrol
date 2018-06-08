package com.oltruong.moneycontrol.analyzer;

import com.oltruong.moneycontrol.operation.Operation;
import com.oltruong.moneycontrol.rule.Rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static com.oltruong.moneycontrol.operation.OperationAsserter.assertThat;


public class BudgetAnalyzerImplTest {

    private BudgetAnalyzer budgetAnalyzer;

    @BeforeEach
    public void setup() {
        budgetAnalyzer = new BudgetAnalyzerImpl();
    }

    @Test
    public void analyzeOperation() throws Exception {

        Rule firstRule = new Rule();
        firstRule.setNameCondition("GROCERY");
        firstRule.setCategory("Supermarket");

        Rule secondRule = new Rule();
        secondRule.setNameCondition("SUPER SHOP");
        secondRule.setCategory("Super shop");
        secondRule.setAmountCondition(123f);
        secondRule.setSubcategory("subcategory");
        secondRule.setRecipient("Recipient");
        secondRule.setComment("Comment");

        Rule thirdRule = new Rule();
        thirdRule.setNameCondition("SUPER SHOP");
        thirdRule.setCategory("Super shop 3");
        thirdRule.setAmountCondition(12f);

        Operation operation = new Operation();
        Date now = new Date();
        operation.setName("GROCERY SUPER SHOP");
        operation.setAmount(123f);
        operation.setId("id");
        operation.setCreationDate(now);

        final Operation analyzedOperation = budgetAnalyzer.analyzeOperation(operation, Arrays.asList(firstRule, secondRule, thirdRule));


        assertThat(analyzedOperation).hasCategory("Super shop")
                                     .hasSubcategory("subcategory")
                                     .hasRecipient("Recipient")
                                     .hasComment("Comment")
                                     .hasCreationDate(now)
                                     .hasId("id");

    }

}