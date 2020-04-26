package com.oltruong.moneycontrol.rules;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author Olivier Truong
 */
@Path("/rest/rules")
//@Consumes("application/json")
@Produces("application/json")
public class RuleResource {

    @GET
    public List<Rule> listAll() {
        return Rule.listAll();
    }

    @GET
    @Path("/{id}")
    public Rule get(@PathParam("id") String id) {
        return Rule.findByIdOptional(id).orElseThrow(NotFoundException::new);
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
