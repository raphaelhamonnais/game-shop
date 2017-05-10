package webservices.rest.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.entity.PhysicalGame;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;
import org.hibernate.ScrollableResults;
import webservices.rest.PaginationHandler;
import webservices.rest.PaginationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.List;
import java.util.stream.Stream;

@Path("physical-games")
@SuppressWarnings("unchecked")
public class PhysicalGameRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.PhysicalGame.COUNT,
                QueryHandler.PhysicalGame.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.PhysicalGame.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        PhysicalGame result = (PhysicalGame) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.PhysicalGame.GET_BY_ID)
                .addParameter(QueryHandler.PhysicalGame.ID_PARAMETER, id)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("stream")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPhysicalGamesStream() {

        ObjectMapper mapper = new ObjectMapper();

        Stream<PhysicalGame> physicalGameStream = (Stream<PhysicalGame>) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.PhysicalGame.GET_ALL)
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
                .createQuery(QueryHandler.PhysicalGame.GET_ALL)
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
}
