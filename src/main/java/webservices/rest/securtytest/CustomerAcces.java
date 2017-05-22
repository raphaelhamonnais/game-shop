package webservices.rest.securtytest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import static javax.ws.rs.core.Response.Status.*;

@Path("customers")
@RolesAllowed({"customer", "admin"})
public class CustomerAcces {

//    /** All users and admins */
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response hello(@Context SecurityContext sc) {
//        // Access allowed if has admin or customer role
//        String userName = sc.getUserPrincipal().getName();
//        String message = "Hello " + userName + " !";
//        return Response.status(OK).entity(message).build();
//    }
//    /** Only admins */
//    @GET
//    @RolesAllowed("admin")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response helloAdmin(@Context SecurityContext sc) {
//        // Access allowed if has admin role
//        String userName = sc.getUserPrincipal().getName();
//        String message = "Hello administrator " + userName + " !";
//        return Response.status(OK).entity(message).build();
//    }
//    /** Only the current customer */
//    @GET
//    @Path("{username}")
//    @RolesAllowed("customer")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response helloCustomer(
//            @Context SecurityContext sc,
//            @PathParam("username") String user) {
//        if (sc.getUserPrincipal().getName().equals(user)) {
//            // Access allowed
//            String message = "Hello customer " + user + " !";
//            return Response.status(OK).entity(message).build();
//        }
//        else {
//            // Access forbidden
//            return Response.status(FORBIDDEN).entity("Wrong user").build();
//        }
//    }
}




