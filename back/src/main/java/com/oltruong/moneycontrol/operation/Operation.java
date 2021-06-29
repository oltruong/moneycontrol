package com.oltruong.moneycontrol.operation;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Optional;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "operation")
public class Operation extends PanacheMongoEntity {

    @BsonId
    public ObjectId id;


    public LocalDate creationDate;

    public Integer year;
    public Integer month;

    public String name;

    public Float amount;

    public String category;
    public String subcategory;

    public String recipient;


    public String comment;

    public static Optional<Operation> findByIdOptional(String id) {

        return find("_id", new ObjectId(id)).firstResultOptional();
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", year=" + year +
                ", month=" + month +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", recipient='" + recipient + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
