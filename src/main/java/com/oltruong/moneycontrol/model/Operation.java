package com.oltruong.moneycontrol.model;


import javax.persistence.*;
import java.util.Date;

/**
 * @author Olivier Truong
 */
@Entity
@NamedQuery(name = "Operation.findByCategoryNotEmpty",
        query = "select o from Operation o where o.category is null or o.category='' or o.category='null'")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date creationDate;

    private Integer year;
    private Integer month;

    private String name;

    private Float amount;

    private String category;
    private String subcategory;

    private String recipient;


    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
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

//    public static List<Operation> all() {
//        return find.orderBy("creationDate desc, name").findList();
//    }
//
//
//    public static List<Operation> unclassified() {
//
//        Query<Operation> query = Ebean.createQuery(Operation.class);
//        query.where(
//                Expr.or(Expr.isNull("category"),
//                        Expr.eq("category", ""))
//        );
//
//        return query.findList();
//
//    }
//
//    public static List<Operation> findByMonth(Integer month) {
//        List<Operation> operationList = all();
//
//        List<Operation> operationListMonth = Lists.newArrayListWithCapacity(operationList.size());
//
//        for (Operation operation : operationList) {
//            if (operation.creationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == month.intValue()) {
//                operationListMonth.add(operation);
//            }
//        }
//
//
//        return operationListMonth;
//    }
//
//    public static void save(Operation operation) {
//        operation.save();
//    }
//
//    public static void delete(Long id) {
//        find.ref(id).delete();
//    }
//
//    public static Operation find(Long id) {
//        return find.byId(id);
//    }


}

