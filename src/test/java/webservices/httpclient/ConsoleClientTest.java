package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ConsoleClientTest extends RestClientTest<ConsoleClient> {

    @Override
    protected ConsoleClient createInstance() {
        return new ConsoleClient();
    }


    @Override
    public void testResourceSpecificServices() throws Exception {
        try {
            HttpResponse<JsonNode> itemById = ((ConsoleClient) classUnderTest).getItemById("PS4");
            assertNotNull(itemById);
            assertNotNull(itemById.getHeaders());
            assertNotNull(itemById.getBody());

            HttpResponse<JsonNode> physicalGames = ((ConsoleClient) classUnderTest).getPhysicalGames("PS4");
            assertNotNull(physicalGames);
            assertNotNull(physicalGames.getHeaders());
            assertNotNull(physicalGames.getBody());

            HttpResponse<JsonNode> games = ((ConsoleClient) classUnderTest).getGames("PS4");
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
