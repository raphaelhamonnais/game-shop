package webservices.rest.resource;

import model.entity.Category;
import model.entity.Game;
import model.handler.HibernateTransactionHandler;
import model.query.CategoryQueriesHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("categories")
@SuppressWarnings("unchecked")
public class CategoryRest {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategories() {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(CategoryQueriesHandler.QUERY_GET_ALL_CATEGORIES)
                .getResultListAndClose();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryByName(@PathParam("name") String name) {
        return (Category) new HibernateTransactionHandler()
                .openSession()
                .createQuery(CategoryQueriesHandler.QUERY_GET_CATEGORY_BY_NAME)
                .addParameter(CategoryQueriesHandler.PARAM_CATEGORY_NAME,name)
                .getUniqueResultAndClose();
    }

    @GET
    @Path("{name}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getGamesFromCategory(@PathParam("name") String name) {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(CategoryQueriesHandler.QUERY_GET_CATEGORY_GAMES)
                .addParameter(CategoryQueriesHandler.PARAM_CATEGORY_NAME,name)
                .getResultListAndClose();
    }
}
