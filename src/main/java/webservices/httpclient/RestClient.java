package webservices.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public abstract class RestClient {

    static com.mashape.unirest.http.ObjectMapper objectMapper = null;
    public static final String ROOT_ADDRESS = "http://localhost:8080/sr03-game-shop/rest";
    public static final String PAGES = "pages";

    protected String ressource;

    public RestClient() {

        if (objectMapper == null)
            objectMapper = initJacksonMapper();
        Unirest.setObjectMapper(objectMapper);

        setRessourceName();
    }

    private com.mashape.unirest.http.ObjectMapper initJacksonMapper() {
        return new com.mashape.unirest.http.ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    protected abstract void setRessourceName();


    protected String buildNumberOfPagesURI() {
        return ROOT_ADDRESS + "/" + ressource + "/" + PAGES;
    }

    protected String buildPageItemsURI(Integer pageNumber) throws UnirestException {
        Integer numberOfPages = getNumberOfPages();

        if (pageNumber > numberOfPages)
            throw new RuntimeException("Page number " + pageNumber + " exceeds the total of pages (=" + numberOfPages + ") available for the " + ressource + " ressource");

        if (pageNumber <= 0)
            throw new RuntimeException("Page number " + pageNumber + " invalid");

        return ROOT_ADDRESS + "/" + ressource + "/" + PAGES + "/" + pageNumber;
    }

    protected String buildItemURI(Integer itemId) {
        return ROOT_ADDRESS + "/" + ressource + "/" + itemId;
    }
    protected String buildItemURI(String itemId) {
        return ROOT_ADDRESS + "/" + ressource + "/" + itemId;
    }

    public Integer getNumberOfPages() throws UnirestException {
        HttpResponse<String> response = Unirest
                .get(buildNumberOfPagesURI())
                .asString();
        return Integer.parseInt(response.getBody());
    }

    public HttpResponse<JsonNode> getPageItems(Integer pageNumber) throws UnirestException {
        return Unirest
                .get(buildPageItemsURI(pageNumber))
                .asJson();
    }

}
