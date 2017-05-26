package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class GameClientTest extends RestClientTest<GameClient> {

    @Override
    protected GameClient createInstance() {
        return new GameClient();
    }


    @Override
    public void testResourceSpecificServices() throws Exception {
        try {
            HttpResponse<JsonNode> itemById = ((GameClient) classUnderTest).getItemById(1);
            assertNotNull(itemById);
            assertNotNull(itemById.getHeaders());
            assertNotNull(itemById.getBody());

            HttpResponse<JsonNode> consoles = ((GameClient) classUnderTest).getConsoles(1);
            assertNotNull(consoles);
            assertNotNull(consoles.getHeaders());
            assertNotNull(consoles.getBody());

            HttpResponse<JsonNode> physicalGames = ((GameClient) classUnderTest).getPhysicalGames(1);
            assertNotNull(physicalGames);
            assertNotNull(physicalGames.getHeaders());
            assertNotNull(physicalGames.getBody());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Exception during client calls to the REST API");
        }
    }
}
