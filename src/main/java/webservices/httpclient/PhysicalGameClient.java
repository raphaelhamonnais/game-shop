package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class PhysicalGameClient extends RestClient {

    public PhysicalGameClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "physical-games";
    }

    public HttpResponse<JsonNode> getItemById(Integer itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }
}

