package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CategoryClientTest extends RestClientTest<CategoryClient> {

    @Override
    protected CategoryClient createInstance() {
        return new CategoryClient();
    }


    @Override
    public void testResourceSpecificServices() throws Exception {
        try {
            HttpResponse<JsonNode> itemById = ((CategoryClient) classUnderTest).getItemById("Action");
            assertNotNull(itemById);
            assertNotNull(itemById.getHeaders());
            assertNotNull(itemById.getBody());

            HttpResponse<JsonNode> games = ((CategoryClient) classUnderTest).getGames("Action");
            assertNotNull(games);
            assertNotNull(games.getHeaders());
            assertNotNull(games.getBody());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Exception during client calls to the REST API");
        }
    }
}
