package webservices.rest;

import model.handler.HibernateTransactionHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public abstract class PaginationService {

    protected PaginationHandler ph;

    public PaginationService() {
        this.ph = setPaginationHandler();
    }

    protected abstract PaginationHandler setPaginationHandler();

    @GET
    @Path("pages")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getNumberOfPages() {
        return Response.ok(ph.getNbPages()).type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("pages/{pageNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPageResults(@PathParam("pageNumber") int pageNumber) {

        if (pageNumber <= 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        if (pageNumber > ph.getNbPages())
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        List results = new HibernateTransactionHandler()
                .openSession()
                .createQuery(ph.getRequestGet())
                .setFirstResult((pageNumber - 1) * ph.getPageSize())
                .setMaxResults(ph.getPageSize())
                .getResultListAndClose();

        return Response.ok(results).type(MediaType.APPLICATION_JSON).build();
    }
}
