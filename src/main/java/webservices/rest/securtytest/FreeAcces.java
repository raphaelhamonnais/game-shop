package webservices.rest.securtytest;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;


@Path("/free")
@PermitAll
public class FreeAcces {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context SecurityContext sc) {
        String s = "";
        s += "user = " + sc.getUserPrincipal() + "\n";
//        s += "user name = " + sc.getUserPrincipal().getName() + "\n";
        return s;
    }


    @POST
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String post(@Context SecurityContext sc) {
        String s = "";
        s += "user = " + sc.getUserPrincipal() + "\n";
        s += "user name = " + sc.getUserPrincipal().getName() + "\n";
        return s;
    }

}
