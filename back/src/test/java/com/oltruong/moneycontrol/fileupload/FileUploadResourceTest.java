package com.oltruong.moneycontrol.fileupload;

import com.oltruong.moneycontrol.operation.Operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class FileUploadResourceTest {

    @Test
    void generateKey() {
        Operation operation = new Operation();
        operation.creationDate = LocalDate.of(2020, 5, 1);
        operation.name = "name";

        String key = new FileUploadResource().generateKey(operation);
        Assertions.assertEquals("2020-05-01name", key);
    }
}