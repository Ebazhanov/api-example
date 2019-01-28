import api.SearchApiJson;
import api.SearchApiRequest;
import api.schema.Result;
import base.BaseClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HelloFresh extends BaseClass {

    @Test
    public void test() {
        final String searchResultsApiJson = SearchApiRequest.makeSearchRequest();
        final SearchApiJson searchApiJson = SearchApiJson.from(searchResultsApiJson);
        final Result result = searchApiJson.getResult();
        assertEquals(result.getName(), "Germany");
        assertEquals(result.getAlpha2Code(), "DE");
        assertEquals(result.getAlpha3Code(), "DEU");
    }
}
