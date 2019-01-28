package api;

import api.schema.Result;
import io.restassured.path.json.JsonPath;

import java.util.List;


public class SearchApiJson {

    private String json;

    public SearchApiJson(String json) {
        this.json = json;
    }

    public List<Result> getResults() {
        return JsonPath.from(json).getList("result", Result.class);
    }

/*    public Set<String> getName() {
        return getResults().stream().map(Result::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }*/
}
