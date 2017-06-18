package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UsersClient extends RestClient {

    public UsersClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "users";
    }

    public HttpResponse<JsonNode> getItemById(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }

    public HttpResponse<JsonNode> getShoppingBags(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/shopping-bags")
                .asJson();
    }

    public HttpResponse<JsonNode> getShoppingBag(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/shopping-bag")
                .asJson();
    }

    public HttpResponse<JsonNode> getOrders(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/orders")
                .asJson();
    }
}

