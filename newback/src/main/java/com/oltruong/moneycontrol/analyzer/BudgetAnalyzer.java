package com.oltruong.moneycontrol.analyzer;

import com.oltruong.moneycontrol.operation.Operation;
import com.oltruong.moneycontrol.rules.Rule;

public interface BudgetAnalyzer {

    Operation analyzeOperation(Operation operation, Iterable<Rule> rules);
}
