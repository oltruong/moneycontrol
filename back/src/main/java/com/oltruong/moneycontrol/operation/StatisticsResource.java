package com.oltruong.moneycontrol.operation;

import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import static com.mongodb.client.model.Accumulators.sum;

/**
 * @author Olivier Truong
 */
@Path("/rest/statistics")
@Produces("application/json")
public class StatisticsResource {

    @GET
    public Iterable<Statistic> listAll(@QueryParam(value = "year") Integer year) {
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();


        if (year == currentYear) {
            final MongoIterable<Statistic> mapy = Operation.mongoDatabase().getCollection("operation")
                                                           .aggregate(
                                                                   Arrays.asList(
                                                                           Aggregates.match(Filters.eq("year", year)),
                                                                           Aggregates.match(Filters.lt("month", currentMonth)),
//                                                                         Aggregates.match(Filters.eq("category", "Nourriture")),
                                                                           Aggregates.group(new Document("category", "$category").append("month", "$month").append("year", "$year"),
                                                                                   sum("totalAmount", "$amount")))
                                                           ).map(Statistic::build);

            return StreamSupport.stream(mapy.spliterator(), false)
                                .collect(Collectors.toList());
        } else {
            final MongoIterable<Statistic> mapy = Operation.mongoDatabase().getCollection("operation")
                                                           .aggregate(
                                                                   Arrays.asList(
                                                                           Aggregates.match(Filters.eq("year", year)),
//                                                                         Aggregates.match(Filters.eq("category", "Nourriture")),
                                                                           Aggregates.group(new Document("category", "$category").append("month", "$month").append("year", "$year"),
                                                                                   sum("totalAmount", "$amount")))
                                                           ).map(Statistic::build);

            return StreamSupport.stream(mapy.spliterator(), false)
                                .collect(Collectors.toList());
        }


//        List<Statistic> target = new ArrayList<>();
//        mapy.forEach(target::add);

//        System.out.println("AAAAAA " + mapy);
//        return target;
//        return toto;

    }

    @Path("/old")
    @GET
    public Iterable listAll2(@QueryParam(value = "year") Integer year, @QueryParam(value = "month") Integer month, @QueryParam(value = "category") String category) {
        System.out.println("BBBBB");

        final MongoIterable<String> aggregate = Operation.mongoDatabase().getCollection("operation")
                                                         .aggregate(
                                                                 Arrays.asList(
                                                                         Aggregates.match(Filters.eq("year", 2020)),
//                                                                         Aggregates.match(Filters.eq("category", "Nourriture")),
                                                                         Aggregates.group(new Document("category", "$category").append("month", "$month").append("year", "$year"),
                                                                                 sum("totalAmount", "$amount")))
                                                         ).map(Document::toJson);


        List toto = new ArrayList();
        aggregate.forEach(tr -> toto.add(tr));
        System.out.println("UUUU " + toto.size());
//
//        System.out.println("AAAAAA ");
        return toto;

    }

}
