package com.oltruong.moneycontrol.analyzer;

import com.oltruong.moneycontrol.operation.Operation;
import com.oltruong.moneycontrol.rule.Rule;

public interface BudgetAnalyzer {

    Operation analyzeOperation(Operation operation, Iterable<Rule> rules);
}
