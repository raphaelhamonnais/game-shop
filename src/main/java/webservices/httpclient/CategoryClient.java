package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CategoryClient extends RestClient {


    public CategoryClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "categories";
    }

    public HttpResponse<JsonNode> getItemById(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }

    public HttpResponse<JsonNode> getGames(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/games")
                .asJson();
    }


}

