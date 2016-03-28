package com.oltruong.moneycontrol.parser;

import com.oltruong.moneycontrol.model.Operation;

import java.util.List;

/**
 * @author oltruong
 */
public class BankFileAnalyzer {

    public static List<Operation> parseString(String fileContent) {

        BankParser bankParser;
        if (fileContent != null) {
            if (fileContent.startsWith("dateOp;dateVal;label;category")) {
                bankParser = new BouBankParser();
            } else if (fileContent.startsWith("Num")) {
                bankParser = new LBPBankParser();
            } else {
                bankParser = new SGBankParser();
            }
            return bankParser.parseString(fileContent);
        } else {
            throw new IllegalArgumentException("Cannot parse null data");
        }

    }
}
