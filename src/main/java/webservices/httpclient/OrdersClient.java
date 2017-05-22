package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class OrdersClient extends RestClient {

    public OrdersClient() {
        super();
    }


    @Override
    protected void setRessourceName() {
        this.ressource = "orders";
    }

    public HttpResponse<JsonNode> getItemById(Integer itemId) throws UnirestException {
        return Unirest
                .get(buildItemURI(itemId))
                .asJson();
    }
}

