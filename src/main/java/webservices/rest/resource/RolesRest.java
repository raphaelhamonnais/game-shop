package webservices.rest.resource;

import model.entity.Roles;
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


@Path("roles")
public class RolesRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.Roles.COUNT,
                QueryHandler.Roles.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Roles.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        Roles result = (Roles) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Roles.GET_BY_ID)
                .addParameter(QueryHandler.Roles.ID_PARAMETER, id)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@PathParam("id") String id) {
        List resultList = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Roles.GET_USERS)
                .addParameter(QueryHandler.Roles.ID_PARAMETER, id)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(resultList).build();
    }
}
