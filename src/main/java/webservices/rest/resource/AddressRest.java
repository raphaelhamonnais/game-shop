package webservices.rest.resource;

import model.entity.Address;
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


@Path("addresses")
public class AddressRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.Address.COUNT,
                QueryHandler.Address.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response address() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Address.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressById(@PathParam("id") int id) {
        Address result = (Address) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Address.GET_BY_ID)
                .addParameter(QueryHandler.Address.ID_PARAMETER, id)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{id}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response users(@PathParam("id") int id) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Address.GET_USERS)
                .addParameter(QueryHandler.Address.ID_PARAMETER, id)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{id}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response orders(@PathParam("id") int id) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Address.GET_ORDERS)
                .addParameter(QueryHandler.Address.ID_PARAMETER, id)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
