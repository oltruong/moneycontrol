package com.oltruong.moneycontrol.parser;

import com.oltruong.moneycontrol.model.Operation;

import java.util.List;

public interface BankParser {

    List<Operation> parseString(String fileContent);
}
