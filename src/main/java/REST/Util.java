package REST;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static Map<String, String> queryToMap(String query) {
        if(!query.equalsIgnoreCase("")) {
            Map<String, String> result = new HashMap<>();
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                }else{
                    result.put(entry[0], "");
                }
            }
            return result;
        }
        return null;
    }
}
