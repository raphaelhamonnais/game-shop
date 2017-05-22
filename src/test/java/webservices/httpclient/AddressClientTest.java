package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class AddressClientTest extends RestClientTest<AddressClient> {

    @Override
    protected AddressClient createInstance() {
        return new AddressClient();
    }


    @Override
    public void testResourceSpecificServices() throws Exception {
        try {
            HttpResponse<JsonNode> itemById = ((AddressClient) classUnderTest).getItemById(1);
            assertNotNull(itemById);
            assertNotNull(itemById.getHeaders());
            assertNotNull(itemById.getBody());

            HttpResponse<JsonNode> orders = ((AddressClient) classUnderTest).getOrders(1);
            assertNotNull(orders);
            assertNotNull(orders.getHeaders());
            assertNotNull(orders.getBody());

            HttpResponse<JsonNode> users = ((AddressClient) classUnderTest).getUsers(1);
            assertNotNull(users);
            assertNotNull(users.getHeaders());
            assertNotNull(users.getBody());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Exception during client calls to the REST API");
        }
    }
}
