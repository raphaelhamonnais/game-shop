package webservices.rest.resource;

import model.entity.Publisher;
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


@Path("publishers")
public class PublisherRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.Publisher.COUNT,
                QueryHandler.Publisher.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Publisher.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Publisher result = (Publisher) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Publisher.GET_BY_ID)
                .addParameter(QueryHandler.Publisher.ID_PARAMETER, id)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames(@PathParam("id") int id) {
        List resultList = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Publisher.GET_GAMES)
                .addParameter(QueryHandler.Publisher.ID_PARAMETER, id)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(resultList).build();
    }
}
