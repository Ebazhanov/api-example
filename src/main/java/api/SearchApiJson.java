package api;

import api.schema.Result;
import io.restassured.path.json.JsonPath;

import java.util.List;


public class SearchApiJson {

    private static final String RESULT_JSON_PATH = "RestResponse.result";

    private final JsonPath jsonPath;

    public static SearchApiJson from(String json) {
        return new SearchApiJson(json);
    }

    private SearchApiJson(String json) {
        this.jsonPath = JsonPath.from(json);
    }

    /**
     * @return get result for one country
     */
    public Result getResult() {
        return jsonPath.getObject(RESULT_JSON_PATH, Result.class);
    }

    /**
     * @return get result for multiple countries in one response
     */
    public List<Result> getResults() {
        return jsonPath.getList(RESULT_JSON_PATH, Result.class);
    }

/*    public Set<String> getName() {
        return getResults().stream().map(Result::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }*/
}
