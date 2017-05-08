package webservices.rest.resource;

import model.entity.Address;
import model.entity.Orders;
import model.entity.Users;
import model.handler.HibernateSessionFactoryHandler;
import model.handler.HibernateTransactionHandler;
import model.query.AddressQueriesHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collection;
import java.util.List;


@Path("addresses")
public class AddressRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response address() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(AddressQueriesHandler.QUERY_GET_ALL_ADDRESSES)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressById(@PathParam("id") int id) {
        Address result = (Address) new HibernateTransactionHandler()
                .openSession()
                .createQuery(AddressQueriesHandler.QUERY_GET_ADDRESS_BY_ID)
                .addParameter(AddressQueriesHandler.PARAM_ADDRESS_NAME, id)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{id}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response users(@PathParam("id") int id) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(AddressQueriesHandler.QUERY_GET_ADDRESS_USERS)
                .addParameter(AddressQueriesHandler.PARAM_ADDRESS_NAME, id)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{id}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response orders(@PathParam("id") int id) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(AddressQueriesHandler.QUERY_GET_ADDRESS_ORDERS)
                .addParameter(AddressQueriesHandler.PARAM_ADDRESS_NAME, id)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
