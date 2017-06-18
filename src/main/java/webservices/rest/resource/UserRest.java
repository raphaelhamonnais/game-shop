package webservices.rest.resource;

import model.entity.Users;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;
import webservices.rest.PaginationHandler;
import webservices.rest.PaginationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("users")
public class UserRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.Users.COUNT,
                QueryHandler.Users.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Users.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("login") String login) {
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
    public Response getShoppingBags(@PathParam("login") String login) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Users.GET_SHOPPING_BAGS)
                .addParameter(QueryHandler.Users.ID_PARAMETER, login)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{login}/shopping-bag")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingBag(@PathParam("login") String login) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Users.GET_OPENED_SHOPPING_BAG)
                .addParameter(QueryHandler.Users.ID_PARAMETER, login)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{login}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(@PathParam("login") String login) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Users.GET_ORDERS)
                .addParameter(QueryHandler.Users.ID_PARAMETER, login)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
