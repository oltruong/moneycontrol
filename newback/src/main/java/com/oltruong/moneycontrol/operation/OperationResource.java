package com.oltruong.moneycontrol.operation;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * @author Olivier Truong
 */
@Path("/rest/operations")
//@Consumes("application/json")
@Produces("application/json")
public class OperationResource {

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


//
//        @RequestMapping(value = "/rest/rules/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    void delete(@PathVariable String id) {
//        Rule rule = getRuleOrThrowException(id);
//        ruleRepository.delete(rule);
//    }


//
//    @RequestMapping(value = "/rest/rules/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    void editRule(@RequestBody Rule rule, @PathVariable String id) {
//
//        getRuleOrThrowException(id);
//        rule.setId(id);
//        ruleRepository.save(rule);
//
//    }
//
//    @RequestMapping(value = "/rest/rules", method = RequestMethod.POST)
//    ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
//        rule.setId(null);
//
//        Rule ruleSaved = ruleRepository.save(rule);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(ruleSaved.getId()).toUri());
//
//        return new ResponseEntity<>(ruleSaved, httpHeaders, HttpStatus.CREATED);
//    }
//

//
//    @RequestMapping(value = "/rest/rules/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    void delete(@PathVariable String id) {
//        Rule rule = getRuleOrThrowException(id);
//        ruleRepository.delete(rule);
//    }
}
