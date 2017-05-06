package webservices.rest.resource;

import model.entity.Address;
import model.entity.Orders;
import model.entity.Users;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.Collection;


@Path("json-test/address")
public class AddressRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Address address(@Context SecurityContext sc) {
        Address address = new Address();
        address.setAdrName("address name");
        address.setAdrCity("Paris");
        address.setAdrStreet("136 rue saint honoré");
        address.setAdrZipCode("75001");
        address.setAdrCountry("France");
        return address;
    }


    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Users> users(@Context SecurityContext sc) {
        Address address = new Address();
        address.setAdrName("address name");
        address.setAdrCity("Paris");
        address.setAdrStreet("136 rue saint honoré");
        address.setAdrZipCode("75001");
        address.setAdrCountry("France");
        return address.getUsers();
    }

    @GET
    @Path("orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Orders> orders(@Context SecurityContext sc) {
        Address address = new Address();
        address.setAdrName("address name");
        address.setAdrCity("Paris");
        address.setAdrStreet("136 rue saint honoré");
        address.setAdrZipCode("75001");
        address.setAdrCountry("France");
        return address.getOrders();
    }
}
