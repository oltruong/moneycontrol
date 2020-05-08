package com.oltruong.moneycontrol.rule;

import org.bson.types.ObjectId;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import static com.oltruong.moneycontrol.rule.Rule.findByIdOptional;

/**
 * @author Olivier Truong
 */
@Path("/rest/rules")
@Produces("application/json")
public class RuleResource {

    @GET
    public List<Rule> listAll() {
        return Rule.listAll();
    }

    @GET
    @Path("/{id}")
    public Rule get(@PathParam("id") String id) {
        return findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Rule.delete("_id", new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Rule rule, @Context UriInfo uriInfo) {
        rule.persist();
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(rule.id.toString());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editRule(Rule rule, @PathParam("id") String id) {
        ObjectId objectId = new ObjectId(id);
        findByIdOptional(objectId).orElseThrow(NotFoundException::new);
        rule.id = objectId;
        rule.persistOrUpdate();
    }

}
