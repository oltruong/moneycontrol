package com.oltruong.moneycontrol.operation;

import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.oltruong.moneycontrol.rule.Rule;

import org.bson.Document;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * @author Olivier Truong
 */
@Path("/rest/classification")
@Produces("application/json")
public class ClassificationResource {

    @GET
    public Iterable<Classification> listAll(@QueryParam(value = "year") Integer year) {

//        LocalDate now = LocalDate.now();
//        int currentYear = now.getYear();
////        int currentMonth = now.getMonthValue();
//
//
//        if (year == currentYear) {
//            final MongoIterable<Statistic> mapy = Operation.mongoDatabase().getCollection("operation")
//                                                           .aggregate(
//                                                                   Arrays.asList(
//                                                                           Aggregates.match(Filters.eq("year", year)),
//                                                                           Aggregates.group(new Document("category", "$category").append("subcategory", "$subcategory")))
//                                                           ).map(Statistic::build);
//
//            return StreamSupport.stream(mapy.spliterator(), false)
//                                .collect(Collectors.toList());
//        } else {
        final MongoIterable<Classification> mapy = Operation.mongoDatabase().getCollection("operation")
                                                            .aggregate(
                                                                    Arrays.asList(
                                                                            Aggregates.match(Filters.eq("year", year)),
                                                                            Aggregates.group(new Document("category", "$category").append("subcategory", "$subcategory")))
                                                            ).map(Classification::build);

        return StreamSupport.stream(mapy.spliterator(), false)
                            .collect(Collectors.toList());


    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(ClassificationChange classificationChange) {
        System.out.println(classificationChange);
        return Response.accepted().build();
//        return Response.created(builder.build()).build();
    }


}
