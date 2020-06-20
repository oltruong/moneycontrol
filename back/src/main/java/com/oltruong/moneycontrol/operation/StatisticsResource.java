package com.oltruong.moneycontrol.operation;

import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import java.time.LocalDate;
import java.util.Arrays;
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
                                                                           Aggregates.group(new Document("category", "$category").append("month", "$month").append("year", "$year"),
                                                                                   sum("totalAmount", "$amount")))
                                                           ).map(Statistic::build);

            return StreamSupport.stream(mapy.spliterator(), false)
                                .collect(Collectors.toList());
        }

    }

}
