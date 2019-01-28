import api.SearchApiRequest;
import base.BaseClass;
import org.testng.annotations.Test;


public class HelloFresh extends BaseClass {

    @Test
    public void test() {
        String searchResultsApiJson = SearchApiRequest.makeSearchRequest();
        System.out.println(searchResultsApiJson);
        System.out.println(searchResultsApiJson);
    }
}
