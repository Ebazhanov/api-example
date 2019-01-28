package api;

import java.util.HashMap;
import java.util.Map;

public class SearchApi {

    private final String name;
    private Map<String, String> searchApiMap = new HashMap<>();

    public SearchApi(String name) {
        this.name = name;

        this.searchApiMap.putAll(searchApiMap);
    }

    public Map<String, String> getSearchApiMap() {
        return searchApiMap;
    }

    public static class SearchApiBuilder {

        private final String name;
        private Map<String, String> searchApiMap = new HashMap<>();

        public SearchApiBuilder(String name) {
            this.name = name;
        }

        public SearchApi build() {
            return new SearchApi(name);
        }
    }
}
