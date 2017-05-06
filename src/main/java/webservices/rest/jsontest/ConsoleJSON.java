package webservices.rest.jsontest;

import dao.ConsoleDao;
import model.Console;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/json-test/consoles")
public class ConsoleJSON {
    private ConsoleDao consoleDao = new ConsoleDao();

    //get all consoles
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Console> getAllConsoles() {
        return consoleDao.getAllConsoles();
    }

    //get console by name
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Console getConsoleByName(@PathParam("name") String name) {
        return consoleDao.getConsoleByName(name);
    }
}