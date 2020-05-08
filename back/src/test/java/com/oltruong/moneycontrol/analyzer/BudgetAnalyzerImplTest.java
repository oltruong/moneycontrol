package com.oltruong.moneycontrol.analyzer;

import com.oltruong.moneycontrol.operation.Operation;
import com.oltruong.moneycontrol.rule.Rule;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

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
        firstRule.nameCondition = "GROCERY";
        firstRule.category = "Supermarket";

        Rule secondRule = new Rule();
        secondRule.nameCondition = "SUPER SHOP";
        secondRule.category = "Super shop";
        secondRule.amountCondition = 123f;
        secondRule.subcategory = "subcategory";
        secondRule.recipient = "Recipient";
        secondRule.comment = "Comment";

        Rule thirdRule = new Rule();
        thirdRule.nameCondition = "SUPER SHOP";
        thirdRule.category = "Super shop 3";
        thirdRule.amountCondition = 12f;

        Operation operation = new Operation();
        LocalDate now = LocalDate.now();
        operation.name = "GROCERY SUPER SHOP";
        operation.amount = 123f;
        final ObjectId id = new ObjectId("5eb55f6ae468c74f7d7d18f1");
        operation.id = id;
        operation.creationDate = now;

        final Operation analyzedOperation = budgetAnalyzer.analyzeOperation(operation, Arrays.asList(firstRule, secondRule, thirdRule));


        assertThat(analyzedOperation).hasCategory("Super shop")
                                     .hasSubcategory("subcategory")
                                     .hasRecipient("Recipient")
                                     .hasComment("Comment")
                                     .hasCreationDate(now)
                                     .hasId(id);

    }

}