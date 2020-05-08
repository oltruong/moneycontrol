package com.oltruong.moneycontrol.operation;

import com.oltruong.moneycontrol.analyzer.BudgetAnalyzer;
import com.oltruong.moneycontrol.rule.Rule;

import org.bson.types.ObjectId;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Path("/rest/operations")
//@Consumes("application/json")
@Produces("application/json")
public class OperationResource {

    @Inject
    private BudgetAnalyzer budgetAnalyzer;

    @GET
    public List<Operation> listAll(@QueryParam(value = "year") Integer year, @QueryParam(value = "month") Integer month, @QueryParam(value = "category") String category) {
        if ("null".equals(category)) {
            return Operation.list("category is null");
        } else if (year != null && month != null) {
            return Operation.list("year= ?1 and month = ?2", year, month);
        } else if (year != null) {
            return Operation.list("year", year);
        } else {
            return Operation.listAll();
        }
    }

    @GET
    @Path("/{id}")
    public Operation get(@PathParam("id") String id) {
        return Operation.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }


    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Operation.delete("_id", new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Operation operation, @Context UriInfo uriInfo) {
        operation.persist();
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(operation.id.toString());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editOperation(Operation operation, @PathParam("id") String id) {
        ObjectId objectId = new ObjectId(id);
        Operation.findByIdOptional(objectId).orElseThrow(NotFoundException::new);
        operation.id = objectId;
        operation.persistOrUpdate();
    }

    @POST
    @Path("/refresh")
    public void refresh() {
        Iterable<Rule> ruleList = Rule.listAll();
        List<Operation> unCategorizedOperations = Operation.list("category is null");
        unCategorizedOperations.forEach(operation -> budgetAnalyzer.analyzeOperation(operation, ruleList));
    }


//
//    @RequestMapping(value = "/rest/operations/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    void editOperation(@RequestBody Operation operation, @PathVariable String id) {
//
//        getOperationOrThrowException(id);
//        operation.setId(id);
//        operationRepository.save(operation);
//
//    }
//
//    @RequestMapping(value = "/rest/operations", method = RequestMethod.POST)
//    ResponseEntity<Operation> createOperation(@RequestBody Operation operation) {
//        operation.setId(null);
//
//        Operation operationSaved = operationRepository.save(operation);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(operationSaved.getId()).toUri());
//
//        return new ResponseEntity<>(operationSaved, httpHeaders, HttpStatus.CREATED);
//    }
//

//
}
