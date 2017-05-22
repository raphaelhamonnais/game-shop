package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class PublisherClientTest extends RestClientTest<PublisherClient> {

    @Override
    protected PublisherClient createInstance() {
        return new PublisherClient();
    }


    @Override
    public void testResourceSpecificServices() throws Exception {
        try {
            HttpResponse<JsonNode> itemById = ((PublisherClient) classUnderTest).getItemById(1);
            assertNotNull(itemById);
            assertNotNull(itemById.getHeaders());
            assertNotNull(itemById.getBody());

            HttpResponse<JsonNode> games = ((PublisherClient) classUnderTest).getGames(1);
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
