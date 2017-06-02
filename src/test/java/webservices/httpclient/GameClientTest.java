package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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


    @Test
    public void testSearch() throws Exception {
        try {
            HttpResponse<JsonNode> searchResult = ((GameClient) classUnderTest).search(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            assertNotNull(searchResult);
            assertNotNull(searchResult.getHeaders());
            assertNotNull(searchResult.getBody());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Exception during client calls to the REST API");
        }
    }

    @Test
    public void testSearchBuildUri() throws Exception {
        Map<String, String> parameters = new HashMap<>();
        String searchURI = "";


        parameters.put("name", null);
        parameters.put("console", null);
        parameters.put("category", null);
        parameters.put("isBest", null);
        parameters.put("isNew", null);
        parameters.put("isHot", null);
        parameters.put("isOnSale", null);
        searchURI = ((GameClient) classUnderTest).buildSearchURI(parameters);
        assertNotNull(searchURI);
        assertEquals("http://localhost:8080/sr03-game-shop/rest/games/search", searchURI);


        parameters.put("name", "aName");
        parameters.put("console", "aConsole");
        parameters.put("category", null);
        parameters.put("isBest", null);
        parameters.put("isNew", null);
        parameters.put("isHot", null);
        parameters.put("isOnSale", null);
        searchURI = ((GameClient) classUnderTest).buildSearchURI(parameters);
        assertNotNull(searchURI);
        assertEquals("http://localhost:8080/sr03-game-shop/rest/games/search?console=aConsole&name=aName", searchURI);

        parameters.put("name", "aName");
        parameters.put("console", "aConsole");
        parameters.put("category", "aCategory");
        parameters.put("isBest", "IsBest");
        parameters.put("isNew", "IsNew");
        parameters.put("isHot", "IsHot");
        parameters.put("isOnSale", "IsOnSale");
        searchURI = ((GameClient) classUnderTest).buildSearchURI(parameters);
        assertNotNull(searchURI);
        assertEquals("http://localhost:8080/sr03-game-shop/rest/games/search?console=aConsole&name=aName&isNew=IsNew&category=aCategory&isOnSale=IsOnSale&isBest=IsBest&isHot=IsHot", searchURI);
    }
}
