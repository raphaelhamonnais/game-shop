package webservices.httpclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public abstract class RestClientTest<T extends RestClient> {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    protected RestClient classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = createInstance();
    }

    protected abstract T createInstance();

    @Test
    public abstract void testResourceSpecificServices() throws Exception;

    @Test
    public void testClient() throws Exception {
        try {
            Integer numberOfPages = classUnderTest.getNumberOfPages();
            assertNotNull(numberOfPages);

            HttpResponse<JsonNode> pageItems = classUnderTest.getPageItems(1);
            assertNotNull(pageItems);
            assertNotNull(pageItems.getHeaders());
            assertNotNull(pageItems.getBody());

            HttpResponse<JsonNode> all = classUnderTest.getAll();
            assertNotNull(all);
            assertNotNull(all.getHeaders());
            assertNotNull(all.getBody());
        }
        catch (Exception e) {
            fail("Exception during client calls to the REST API");
        }
    }


    @Test
    public void tooLittlePageNumberThrowsException() throws Exception {
        expectedEx.expect(RuntimeException.class);
        classUnderTest.getPageItems(0);
    }

    @Test
    public void tooBigPageNumberThrowsException() throws Exception {
        expectedEx.expect(RuntimeException.class);
        classUnderTest.getPageItems(Integer.MAX_VALUE);
    }
}
