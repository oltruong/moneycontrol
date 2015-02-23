package fr.oltruong.moneycontrol.controller;

import fr.oltruong.moneycontrol.model.Operation;
import fr.oltruong.moneycontrol.model.Rule;

/**
 * @author Olivier Truong
 */
public class BudgetAnalyzer {

    public static Operation analyzeOperation(Operation operation, Iterable<Rule> rules) {
        for (Rule rule : rules) {
            operation = analyze(operation, rule);
        }
        return operation;
    }


    private static Operation analyze(Operation operation, Rule rule) {
        if (operationMatchesRule(operation, rule)) {
            operation.setCategory(rule.getCategory());
            operation.setSubcategory(rule.getSubcategory());
            operation.setRecipient(rule.getRecipient());
            operation.setComment(rule.getComment());
        }
        return operation;
    }

    private static boolean operationMatchesRule(Operation operation, Rule rule) {
        return operation.getName().toLowerCase().contains(rule.getNameCondition().toLowerCase()) && (rule.getAmountCondition() == null || rule.getAmountCondition().equals(operation.getAmount()));
    }
}