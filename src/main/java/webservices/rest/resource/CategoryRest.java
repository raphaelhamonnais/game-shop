package webservices.rest.resource;

import model.entity.Category;
import model.entity.Game;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;
import webservices.rest.PaginationHandler;
import webservices.rest.PaginationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("categories")
@SuppressWarnings("unchecked")
public class CategoryRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.Category.COUNT,
                QueryHandler.Category.GET_ALL);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Category.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("name") String name) {
        Category result = (Category) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Category.GET_BY_ID)
                .addParameter(QueryHandler.Category.ID_PARAMETER, name)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{name}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames(@PathParam("name") String name) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Category.GET_GAMES)
                .addParameter(QueryHandler.Category.ID_PARAMETER, name)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
