import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ScenarioContext {

    private static Map<Integer, Map<String, Object>> scenarioContext = new ConcurrentHashMap<>();

    public static void init(int testCaseId) {
        scenarioContext.put(testCaseId, new HashMap<>());
    }

    public static void remove(int testCaseId) {
        scenarioContext.remove(testCaseId);
    }

    public static void put(int testCaseId, String alias, @NonNull Object object) {
        Map<String, Object> localMap = scenarioContext.getOrDefault(testCaseId, new HashMap<>());
        localMap.put(alias, object);
        scenarioContext.put(testCaseId, localMap);
    }

    public static <T> T get(int testCaseId, String alias, Class<T> clazz) {
        return clazz.cast(scenarioContext.get(testCaseId).get(alias));
    }

}


