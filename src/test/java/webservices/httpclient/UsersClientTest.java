package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class UsersClientTest extends RestClientTest<UsersClient> {

    @Override
    protected UsersClient createInstance() {
        return new UsersClient();
    }


    @Override
    public void testResourceSpecificServices() throws Exception {
        try {
            HttpResponse<JsonNode> itemById = ((UsersClient) classUnderTest).getItemById("satince");
            assertNotNull(itemById);
            assertNotNull(itemById.getHeaders());
            assertNotNull(itemById.getBody());

            HttpResponse<JsonNode> orders = ((UsersClient) classUnderTest).getOrders("satince");
            assertNotNull(orders);
            assertNotNull(orders.getHeaders());
            assertNotNull(orders.getBody());

            HttpResponse<JsonNode> shoppingBags = ((UsersClient) classUnderTest).getShoppingBags("satince");
            assertNotNull(shoppingBags);
            assertNotNull(shoppingBags.getHeaders());
            assertNotNull(shoppingBags.getBody());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Exception during client calls to the REST API");
        }
    }
}
