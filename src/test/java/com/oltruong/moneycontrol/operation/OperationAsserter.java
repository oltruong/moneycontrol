package com.oltruong.moneycontrol.operation;

import org.assertj.core.api.Assertions;

import java.util.Date;

public class OperationAsserter {


    private final Operation operation;

    public OperationAsserter(Operation operation) {
        this.operation = operation;
    }

    public static OperationAsserter assertThat(Operation operation) {
        OperationAsserter operationAsserter = new OperationAsserter(operation);
        return operationAsserter;

    }


    private void checkNotNull() {
        Assertions.assertThat(operation).isNotNull();
    }

    public OperationAsserter hasName(String name) {
        checkNotNull();
        Assertions.assertThat(operation.getName()).as("Operation name").isEqualTo(name);
        return this;
    }

    public OperationAsserter hasAmount(float amount) {
        checkNotNull();
        Assertions.assertThat(operation.getAmount()).as("Operation amount").isEqualTo(amount);
        return this;
    }

    public OperationAsserter hasYear(int year) {
        checkNotNull();
        Assertions.assertThat(operation.getYear()).as("Operation year").isEqualTo(year);
        return this;
    }

    public OperationAsserter hasMonth(int month) {
        checkNotNull();
        Assertions.assertThat(operation.getMonth()).as("Operation month").isEqualTo(month);
        return this;
    }

    public OperationAsserter hasCategory(String category) {
        checkNotNull();
        Assertions.assertThat(operation.getCategory()).as("Operation category").isEqualTo(category);
        return this;
    }

    public OperationAsserter hasSubcategory(String subcategory) {
        checkNotNull();
        Assertions.assertThat(operation.getSubcategory()).as("Operation category").isEqualTo(subcategory);
        return this;
    }

    public OperationAsserter hasComment(String comment) {
        checkNotNull();
        Assertions.assertThat(operation.getComment()).as("Operation comment").isEqualTo(comment);
        return this;
    }

    public OperationAsserter hasCreationDate(Date creationDate) {
        checkNotNull();
        Assertions.assertThat(operation.getCreationDate()).as("Operation creation date").isEqualTo(creationDate);
        return this;
    }

    public OperationAsserter hasId(String id) {
        checkNotNull();
        Assertions.assertThat(operation.getId()).as("Operation id").isEqualTo(id);
        return this;
    }

    public OperationAsserter hasRecipient(String recipient) {
        checkNotNull();
        Assertions.assertThat(operation.getRecipient()).as("Operation recipient").isEqualTo(recipient);
        return this;
    }
}
