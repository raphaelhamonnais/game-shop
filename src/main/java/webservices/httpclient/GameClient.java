package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GameClient extends RestClient {

    public GameClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "games";
    }

    public HttpResponse<JsonNode> getItemById(Integer itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }

    public HttpResponse<JsonNode> getConsoles(Integer itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/consoles")
                .asJson();
    }

    public HttpResponse<JsonNode> getPhysicalGames(Integer itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId) + "/physical-games")
                .asJson();
    }


}

