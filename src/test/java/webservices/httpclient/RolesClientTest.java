package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class RolesClientTest extends RestClientTest<RolesClient> {

    @Override
    protected RolesClient createInstance() {
        return new RolesClient();
    }


    @Override
    public void testResourceSpecificServices() throws Exception {
        try {
            HttpResponse<JsonNode> itemById = ((RolesClient) classUnderTest).getItemById("customer");
            assertNotNull(itemById);
            assertNotNull(itemById.getHeaders());
            assertNotNull(itemById.getBody());

            HttpResponse<JsonNode> users = ((RolesClient) classUnderTest).getUsers("customer");
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
