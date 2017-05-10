package webservices.rest.resource;

import model.entity.Orders;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;
import webservices.rest.PaginationHandler;
import webservices.rest.PaginationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("orders")
public class OrdersRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.Order.COUNT,
                QueryHandler.Order.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orders() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Order.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") int id) {
        Orders result = (Orders) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Order.GET_BY_ID)
                .addParameter(QueryHandler.Order.ID_PARAMETER, id)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteOrder(@PathParam("id") int id) {
        new HibernateTransactionHandler()
                .openSession()
                .beginTransaction()
                .createQuery(QueryHandler.Order.DELETE_BY_ID)
                .addParameter(QueryHandler.Order.ID_PARAMETER, id)
                .executeUpdate()
                .commit()
                .closeSession();
        return Response.status(Response.Status.OK)
                .entity("Successful delete of order with id = " + id)
                .build();
    }
}
