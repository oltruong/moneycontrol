package com.oltruong.moneycontrol.operation;

import org.assertj.core.api.Assertions;
import org.bson.types.ObjectId;

import java.time.LocalDate;
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
        Assertions.assertThat(operation.name).as("Operation name").isEqualTo(name);
        return this;
    }

    public OperationAsserter hasAmount(float amount) {
        checkNotNull();
        Assertions.assertThat(operation.amount).as("Operation amount").isEqualTo(amount);
        return this;
    }

    public OperationAsserter hasYear(int year) {
        checkNotNull();
        Assertions.assertThat(operation.year).as("Operation year").isEqualTo(year);
        return this;
    }

    public OperationAsserter hasMonth(int month) {
        checkNotNull();
        Assertions.assertThat(operation.month).as("Operation month").isEqualTo(month);
        return this;
    }

    public OperationAsserter hasCategory(String category) {
        checkNotNull();
        Assertions.assertThat(operation.category).as("Operation category").isEqualTo(category);
        return this;
    }

    public OperationAsserter hasSubcategory(String subcategory) {
        checkNotNull();
        Assertions.assertThat(operation.subcategory).as("Operation category").isEqualTo(subcategory);
        return this;
    }

    public OperationAsserter hasComment(String comment) {
        checkNotNull();
        Assertions.assertThat(operation.comment).as("Operation comment").isEqualTo(comment);
        return this;
    }

    public OperationAsserter hasCreationDate(LocalDate creationDate) {
        checkNotNull();
        Assertions.assertThat(operation.creationDate).as("Operation creation date").isEqualTo(creationDate);
        return this;
    }

    public OperationAsserter hasId(ObjectId id) {
        checkNotNull();
        Assertions.assertThat(operation.id).as("Operation id").isEqualTo(id);
        return this;
    }

    public OperationAsserter hasRecipient(String recipient) {
        checkNotNull();
        Assertions.assertThat(operation.recipient).as("Operation recipient").isEqualTo(recipient);
        return this;
    }
}
