package com.oltruong.moneycontrol.parser;

import com.oltruong.moneycontrol.model.Operation;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author oltruong
 */
public abstract class BankParserTest {

    protected void checkOperation(Operation operation, String name, float amount, int year, int month) {
        assertThat(operation.getName()).isEqualTo(name);
        assertThat(operation.getAmount()).isEqualTo(amount);
        assertThat(operation.getYear()).isEqualTo(year);
        assertThat(operation.getMonth()).isEqualTo(month);
    }

}

