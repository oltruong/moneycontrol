package com.oltruong.moneycontrol.rule;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Olivier Truong
 */

@Document
public class Rule {

    @Id
    private String id;

    private int ruleOrder;
    private String nameCondition;
    private Float amountCondition;
    private String category;
    private String subcategory;
    private String recipient;
    private String comment;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRuleOrder() {
        return ruleOrder;
    }

    public void setRuleOrder(int ruleOrder) {
        this.ruleOrder = ruleOrder;
    }

    public String getNameCondition() {
        return nameCondition;
    }

    public void setNameCondition(String nameCondition) {
        this.nameCondition = nameCondition;
    }

    public Float getAmountCondition() {
        return amountCondition;
    }

    public void setAmountCondition(Float amountCondition) {
        this.amountCondition = amountCondition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
