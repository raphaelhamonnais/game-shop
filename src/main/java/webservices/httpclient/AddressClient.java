package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AddressClient extends RestClient {

    public AddressClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "addresses";
    }

    public HttpResponse<JsonNode> getItemById(int itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }

    public HttpResponse<JsonNode> getUsers(int itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/users")
                .asJson();
    }

    public HttpResponse<JsonNode> getOrders(int itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/orders")
                .asJson();
    }


}

