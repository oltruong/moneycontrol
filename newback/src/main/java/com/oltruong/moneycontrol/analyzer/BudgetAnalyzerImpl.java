package com.oltruong.moneycontrol.analyzer;

import com.oltruong.moneycontrol.operation.Operation;
import com.oltruong.moneycontrol.rules.Rule;

import javax.enterprise.context.ApplicationScoped;


/**
 * @author Olivier Truong
 */
@ApplicationScoped
public final class BudgetAnalyzerImpl implements BudgetAnalyzer {

    @Override
    public Operation analyzeOperation(Operation operation, Iterable<Rule> rules) {
        Operation operationAnalyzed = operation;
        for (Rule rule : rules) {
            operationAnalyzed = analyze(operation, rule);
        }
        return operationAnalyzed;
    }

    private Operation analyze(Operation operation, Rule rule) {
        if (operationMatchesRule(operation, rule)) {
            operation.category = rule.category;
            operation.subcategory = rule.subcategory;
            operation.recipient = rule.recipient;
            operation.comment = rule.comment;
        }
        return operation;
    }

    private boolean operationMatchesRule(Operation operation, Rule rule) {
        return operation.name.toLowerCase().contains(rule.nameCondition.toLowerCase())
                && (rule.amountCondition == null || rule.amountCondition.equals(operation.amount));
    }
}