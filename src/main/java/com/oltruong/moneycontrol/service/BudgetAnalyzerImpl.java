package com.oltruong.moneycontrol.service;

import com.oltruong.moneycontrol.model.Operation;
import com.oltruong.moneycontrol.model.Rule;

import org.springframework.stereotype.Service;

/**
 * @author Olivier Truong
 */
@Service
public final class BudgetAnalyzerImpl implements BudgetAnalyzer {

    @Override
    public  Operation analyzeOperation(Operation operation, Iterable<Rule> rules) {
        Operation operationAnalyzed = operation;
        for (Rule rule : rules) {
            operationAnalyzed = analyze(operation, rule);
        }
        return operationAnalyzed;
    }

    private  Operation analyze(Operation operation, Rule rule) {
        if (operationMatchesRule(operation, rule)) {
            operation.setCategory(rule.getCategory());
            operation.setSubcategory(rule.getSubcategory());
            operation.setRecipient(rule.getRecipient());
            operation.setComment(rule.getComment());
        }
        return operation;
    }

    private  boolean operationMatchesRule(Operation operation, Rule rule) {
        return operation.getName().toLowerCase().contains(rule.getNameCondition().toLowerCase())
                && (rule.getAmountCondition() == null || rule.getAmountCondition().equals(operation.getAmount()));
    }
}