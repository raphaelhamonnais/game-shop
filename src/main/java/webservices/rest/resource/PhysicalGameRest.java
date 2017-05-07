package webservices.rest.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.entity.PhysicalGame;
import model.handler.HibernateTransactionHandler;
import model.query.PhysicalGameQueriesHandler;
import org.hibernate.ScrollableResults;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.List;
import java.util.stream.Stream;

@Path("physical-games")
@SuppressWarnings("unchecked")
public class PhysicalGameRest {

    private static final int pageSize = 20;
    private long nbPages = getNbPages();

    private long getNbPages() {
        Long nbPhysicalGames = (Long) new HibernateTransactionHandler()
                .openSession()
                .createQuery(PhysicalGameQueriesHandler.QUERY_COUNT_PHYSICAL_GAMES)
                .getUniqueResultAndClose();

        Double nbPages = Math.ceil(nbPhysicalGames.doubleValue() / pageSize);
        return nbPages.longValue();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PhysicalGame> getAllPhysicalGames() {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(PhysicalGameQueriesHandler.QUERY_GET_ALL_PHYSICAL_GAMES)
                .getResultListAndClose();
    }


    @GET
    @Path("pages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfPages() {
        return Response.ok("{" + nbPages + "}").type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("pages/{pageNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPageResults(@PathParam("pageNumber") int pageNumber) {

        if (pageNumber <= 0) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        else {
            List results = new HibernateTransactionHandler()
                    .openSession()
                    .createQuery(PhysicalGameQueriesHandler.QUERY_GET_ALL_PHYSICAL_GAMES)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultListAndClose();
            return Response.ok(results).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("stream")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPhysicalGamesStream() {

        ObjectMapper mapper = new ObjectMapper();

        Stream<PhysicalGame> physicalGameStream = (Stream<PhysicalGame>) new HibernateTransactionHandler()
                .openSession()
                .createQuery(PhysicalGameQueriesHandler.QUERY_GET_ALL_PHYSICAL_GAMES)
                .stream();

        StreamingOutput stream = new StreamingOutput() {
            private boolean isBeginning = true;
            @Override
            public void write(OutputStream os) throws IOException, WebApplicationException {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write("[");
                physicalGameStream.
                        forEach(physicalGame -> {
                            try {
                                if (isBeginning)
                                    isBeginning = false;
                                else
                                    writer.write(",");
                                writer.write(mapper.writeValueAsString(physicalGame));
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                writer.write("]");
                writer.flush();
            }
        };
//        session.close();
        return Response.ok(stream).type(MediaType.APPLICATION_JSON).build();
    }



    @GET
    @Path("scroll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPhysicalGamesScroll() {
        ObjectMapper mapper = new ObjectMapper();
        ScrollableResults scrollableResults = new HibernateTransactionHandler()
                .openSession()
                .createQuery(PhysicalGameQueriesHandler.QUERY_GET_ALL_PHYSICAL_GAMES)
                .scrollForward();

        StreamingOutput stream = new StreamingOutput() {
            private boolean isBeginning = true;
            @Override
            public void write(OutputStream os) throws IOException, WebApplicationException {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write("[");
                while (scrollableResults.next()) {
                    if (isBeginning)
                        isBeginning = false;
                    else
                        writer.write(",");
                    PhysicalGame pg = (PhysicalGame) scrollableResults.get(0);
                    writer.write(mapper.writeValueAsString(pg));
                    writer.flush();
                }
                writer.write("]");
                writer.flush();
            }
        };
//        session.close();
        return Response.ok(stream).type(MediaType.APPLICATION_JSON).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PhysicalGame getPhysicalGameById(@PathParam("id") int id) {
        return (PhysicalGame) new HibernateTransactionHandler()
                .openSession()
                .createQuery(PhysicalGameQueriesHandler.QUERY_GET_PHYSICAL_GAME_BY_ID)
                .addParameter(PhysicalGameQueriesHandler.PARAM_PHYSICAL_GAME_ID, id)
                .getUniqueResultAndClose();
    }
}
