package api;

import api.schema.Result;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

import java.util.List;


public class ApiJson {

    private static final String RESULT_JSON_PATH = "RestResponse.result";

    private final JsonPath jsonPath;

    private ApiJson(String json) {
        this.jsonPath = JsonPath.from(json);
    }

    public static ApiJson from(String json) {
        return new ApiJson(json);
    }

    /**
     * @return get result for one country
     */
    @Step
    public Result getResult() {
        return jsonPath.getObject(RESULT_JSON_PATH, Result.class);
    }

    /**
     * @return get result for multiple countries in one response
     */
    @Step
    public List<Result> getResults() {
        return jsonPath.getList(RESULT_JSON_PATH, Result.class);
    }

}
