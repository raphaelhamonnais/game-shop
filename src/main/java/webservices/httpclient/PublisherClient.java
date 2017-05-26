package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class PublisherClient extends RestClient {

    public PublisherClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "publishers";
    }

    public HttpResponse<JsonNode> getItemById(Integer itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }

    public HttpResponse<JsonNode> getGames(Integer itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/games")
                .asJson();
    }


}

