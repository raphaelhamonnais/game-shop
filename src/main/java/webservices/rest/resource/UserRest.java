package webservices.rest.resource;

import model.entity.Users;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("users")
public class UserRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response users() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Users.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByLogin(@PathParam("login") String login) {
        Users result = (Users) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Users.GET_BY_ID)
                .addParameter(QueryHandler.Users.ID_PARAMETER, login)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{login}/shopping-bags")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shoppingBags(@PathParam("login") String login) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Users.GET_SHOPPING_BAGS)
                .addParameter(QueryHandler.Users.ID_PARAMETER, login)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{login}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response orders(@PathParam("login") String login) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Users.GET_ORDERS)
                .addParameter(QueryHandler.Users.ID_PARAMETER, login)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
