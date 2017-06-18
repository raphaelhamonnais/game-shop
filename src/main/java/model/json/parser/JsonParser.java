package model.json.parser;




import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T parseJsonObject(String input, Class<T> targetClass) throws IOException {
        return objectMapper.readValue(input, targetClass);
    }

    public <T> List<T> parseJsonListOfObjects(String input, Class<T[]> targetClass) throws IOException {
        return Arrays.asList(objectMapper.readValue(input, targetClass));
    }
}
