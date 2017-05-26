package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RolesClient extends RestClient {

    public RolesClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "roles";
    }

    public HttpResponse<JsonNode> getItemById(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }

    public HttpResponse<JsonNode> getUsers(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/users")
                .asJson();
    }

}

