package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class PhysicalGameClientTest extends RestClientTest<PhysicalGameClient> {

    @Override
    protected PhysicalGameClient createInstance() {
        return new PhysicalGameClient();
    }


    @Override
    public void testResourceSpecificServices() throws Exception {
        try {
            HttpResponse<JsonNode> itemById = ((PhysicalGameClient) classUnderTest).getItemById(1);
            assertNotNull(itemById);
            assertNotNull(itemById.getHeaders());
            assertNotNull(itemById.getBody());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Exception during client calls to the REST API");
        }
    }
}
