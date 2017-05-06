package webservices.rest.resource;

import model.dao.CategoryDao;
import model.dao.CategoryDaoBis;
import model.entity.Category;
import model.entity.Game;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("json-test/categories")
public class CategoryJSON {
    private CategoryDao categoryDao = new CategoryDao();

    //get all categories
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    //get category by name
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryByName(@PathParam("name") String name) {
        return categoryDao.getCategoryByName(name);
    }

    //get games from category
    @GET
    @Path("{name}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getGamesFromCategory(@PathParam("name") String name) {
//        return categoryDao.getGamesFromCategory();
        return new CategoryDaoBis().getGames(name);
    }
}
