package webservices.rest.resource;

import model.dao.PhysicalGameDao;
import model.entity.PhysicalGame;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("physical-games")
public class PhysicalGameRest {
    private PhysicalGameDao physicalGameDao = new PhysicalGameDao();

    //get all physical games
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PhysicalGame> getAllPhysicalGames() {
        return physicalGameDao.getAllPhysicalGames();
    }

    //get physical game by id
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PhysicalGame getPhysicalGameById(@PathParam("id") int id) {
        return physicalGameDao.getPhysicalGameById(id);
    }
}
