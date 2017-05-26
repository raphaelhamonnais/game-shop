package model.json.parser;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import webservices.httpclient.*;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class JsonParserTest {

    private static final Logger LOGGER = LogManager.getLogger(JsonParserTest.class);

    private JsonParser jsonParser = new JsonParser();

    private <T> void testParsing(String pageItems, String itemById, Class<T> targetClass, Class<T[]> targetClassList) {
        try {
            T item = jsonParser.parseJsonObject(itemById, targetClass);
            assertNotNull(item);

            List<T> list = jsonParser.parseJsonListOfObjects(pageItems, targetClassList);
            assertNotNull(list);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }


    @Test
    public void parsingAddressTest() throws Exception {
        RestClient restClient = new AddressClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((AddressClient) restClient).getItemById(1);

        try {
//            List<Address> addresses = jsonParser.parseJsonListOfObjects(pageItems.getBody().toString(), Address[].class);
//            assertNotNull(addresses);
//
//            Address address = jsonParser.parseJsonObject(itemById.getBody().toString(), Address.class);
//            assertNotNull(address);
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), Address.class, Address[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }

    @Test
    public void parsingCategoryTest() throws Exception {
        RestClient restClient = new CategoryClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((CategoryClient) restClient).getItemById("Action");

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), Category.class, Category[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }

    @Test
    public void parsingConsoleTest() throws Exception {
        RestClient restClient = new ConsoleClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((ConsoleClient) restClient).getItemById("PS4");

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), Console.class, Console[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }


    @Test
    public void parsingGameTest() throws Exception {
        RestClient restClient = new GameClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((GameClient) restClient).getItemById(1);

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), Game.class, Game[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }


    @Test
    public void parsingOrdersTest() throws Exception {
        RestClient restClient = new OrdersClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((OrdersClient) restClient).getItemById(1);

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), Orders.class, Orders[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }


    @Test
    public void parsingPhysicalGameTest() throws Exception {
        RestClient restClient = new PhysicalGameClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((PhysicalGameClient) restClient).getItemById(1);

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), PhysicalGame.class, PhysicalGame[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }


    @Test
    public void parsingPublisherTest() throws Exception {
        RestClient restClient = new PublisherClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((PublisherClient) restClient).getItemById(1);

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), Publisher.class, Publisher[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }


    @Test
    public void parsingRolesTest() throws Exception {
        RestClient restClient = new RolesClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((RolesClient) restClient).getItemById("customer");

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), Roles.class, Roles[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }

    @Test
    public void parsingShoppingBagTest() throws Exception {
        RestClient restClient = new ShoppingBagClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((ShoppingBagClient) restClient).getItemById(1);

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), ShoppingBag.class, ShoppingBag[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }


    @Test
    public void parsingUsersTest() throws Exception {
        RestClient restClient = new UsersClient();

        HttpResponse<JsonNode> pageItems = restClient.getPageItems(1);
        HttpResponse<JsonNode> itemById = ((UsersClient) restClient).getItemById("satince");

        try {
            testParsing(pageItems.getBody().toString(), itemById.getBody().toString(), Users.class, Users[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Error happened during json parsing");
        }
    }

}
