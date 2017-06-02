package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

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

    public HttpResponse<JsonNode> search(String name,
                                         String console,
                                         String category,
                                         String isBest,
                                         String isNew,
                                         String isHot,
                                         String isOnSale) throws UnirestException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("console", console);
        parameters.put("category", category);
        parameters.put("isBest", isBest);
        parameters.put("isNew", isNew);
        parameters.put("isHot", isHot);
        parameters.put("isOnSale", isOnSale);
        return Unirest
                .get(buildSearchURI(parameters))
                .asJson();
    }




    protected String buildSearchURI(Map<String, String> parameters) {

        StringBuilder sb = new StringBuilder(ROOT_ADDRESS + "/" + ressource + "/" + "search");


        Boolean firstParameterPut = false;
        for (String key : parameters.keySet()) {
            String paramValue = parameters.get(key);
            if (paramValue != null) {
                String paramDelimiter = firstParameterPut ? "&" : "?";
                sb.append(paramDelimiter).append(key).append("=").append(paramValue);
                firstParameterPut = true;
            }
        }
        System.out.println("Search URI = " + sb.toString());
        return sb.toString();
    }
}

