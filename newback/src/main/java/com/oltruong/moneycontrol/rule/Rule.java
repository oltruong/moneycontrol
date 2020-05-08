package com.oltruong.moneycontrol.rule;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Optional;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;

@MongoEntity(collection = "rule")
public class Rule extends PanacheMongoEntityBase {

    @BsonId
    public ObjectId id;

    public String nameCondition;
    public Float amountCondition;
    public String category;
    public String subcategory;
    public String recipient;
    public String comment;

    public static Optional<Rule> findByIdOptional(String id) {
        return find("_id", new ObjectId(id)).firstResultOptional();
    }


}
