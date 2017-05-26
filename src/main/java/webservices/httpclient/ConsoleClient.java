package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ConsoleClient extends RestClient {

    public ConsoleClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "consoles";
    }

    public HttpResponse<JsonNode> getItemById(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }

    public HttpResponse<JsonNode> getPhysicalGames(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/physical-games")
                .asJson();
    }

    public HttpResponse<JsonNode> getGames(String itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/games")
                .asJson();
    }


}

