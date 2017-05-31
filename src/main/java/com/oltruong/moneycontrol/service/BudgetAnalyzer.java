package com.oltruong.moneycontrol.service;

import com.oltruong.moneycontrol.model.Operation;
import com.oltruong.moneycontrol.model.Rule;

public interface BudgetAnalyzer {

    Operation analyzeOperation(Operation operation, Iterable<Rule> rules);
}
