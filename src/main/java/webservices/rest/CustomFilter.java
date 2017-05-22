
package webservices.rest;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

import java.util.Collections;
import java.util.List;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Provider
@Authentification
@Priority(Priorities.AUTHENTICATION)
public class CustomFilter implements ResourceFilter, ContainerRequestFilter {
    private @Context SecurityContext sc;
    private final boolean denyAll;
    private final String[] rolesAllowed;

    protected CustomFilter() {
        this.denyAll = true;
        this.rolesAllowed = null;
    }

    protected CustomFilter(String[] rolesAllowed) {
        this.denyAll = false;
        this.rolesAllowed = (rolesAllowed != null) ? rolesAllowed : new String[] {};
    }

    // ResourceFilter

    @Override
    public ContainerRequestFilter getRequestFilter() {
        return this;
    }

    @Override
    public ContainerResponseFilter getResponseFilter() {
        return null;
    }

    // ContainerRequestFilter

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        System.out.println("here");

        if (!denyAll) {
            for (String role : rolesAllowed) {
                if (sc.isUserInRole(role))
                    return request;
            }
        }



//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            try {
//                facesContext.getExternalContext().redirect("/login.html");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//            Response.ResponseBuilder builder = null;
//            String response = "Custom message";
//            builder = Response.status(Response.Status.UNAUTHORIZED).entity(response);
//            throw new WebApplicationException(builder.build());
        return null;
    }
}


