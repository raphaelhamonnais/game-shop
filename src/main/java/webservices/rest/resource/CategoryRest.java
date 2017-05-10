package webservices.rest.resource;

import model.entity.Category;
import model.entity.Game;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;

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
                .createQuery(QueryHandler.Category.GET_ALL)
                .getResultListAndClose();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryByName(@PathParam("name") String name) {
        return (Category) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Category.GET_BY_ID)
                .addParameter(QueryHandler.Category.ID_PARAMETER, name)
                .getUniqueResultAndClose();
    }

    @GET
    @Path("{name}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getGamesFromCategory(@PathParam("name") String name) {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Category.GET_GAMES)
                .addParameter(QueryHandler.Category.ID_PARAMETER,name)
                .getResultListAndClose();
    }

//    @DELETE
//    @Path("{name}")
//    public Response delete(@PathParam("name") String name) {
//        new HibernateTransactionHandler()
//                .openSession()
//                .beginTransaction()
//                .createQuery(CategoryQueriesHandler.DELETE_CATEGORY_BY_NAME)
//                .addParameter(CategoryQueriesHandler.PARAM_CATEGORY_NAME,name)
//                .executeUpdate()
//                .commit()
//                .closeSession();
//        return Response
//                .status(ACCEPTED)
//                .entity("Delete of category " + name + " succesful")
//                .build();
//    }
}
