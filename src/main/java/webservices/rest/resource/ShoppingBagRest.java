package webservices.rest.resource;

import model.entity.ShoppingBag;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;
import webservices.rest.PaginationHandler;
import webservices.rest.PaginationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("shopping-bags")
public class ShoppingBagRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.ShoppingBag.COUNT,
                QueryHandler.ShoppingBag.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.ShoppingBag.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        ShoppingBag result = (ShoppingBag) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.ShoppingBag.GET_BY_ID)
                .addParameter(QueryHandler.ShoppingBag.ID_PARAMETER, id)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


}
