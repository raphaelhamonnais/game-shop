package webservices.rest.securtytest;

import webservices.rest.Authentification;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;


@Path("/admin")
public class AdminAcces {


    @Authentification
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context SecurityContext sc) {
        String s = "";
        s += "user = " + sc.getUserPrincipal();
        return s;
    }
}
