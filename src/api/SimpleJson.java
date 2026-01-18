package api;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Tiny JSON helpers for flat JSON objects (no external libs).
 * Sufficient for simple coursework payloads.
 */
public class SimpleJson {

    public static String readAll(InputStream in) throws Exception {
        byte[] data = in.readAllBytes();
        return new String(data, StandardCharsets.UTF_8);
    }

    /**
     * Parse a flat JSON object like {"firstName":"hari","lastName":"thapa"}
     */
    public static Map<String,String> parseFlat(String json) {
        Map<String,String> map = new HashMap<>();
        if (json == null) return map;
        String s = json.trim();
        if (s.startsWith("{")) s = s.substring(1);
        if (s.endsWith("}")) s = s.substring(0, s.length()-1);
        if (s.isBlank()) return map;
        String[] parts = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (String p : parts) {
            String[] kv = p.split(":",2);
            if (kv.length < 2) continue;
            String k = kv[0].trim().replaceAll("^\"|\"$", "");
            String v = kv[1].trim().replaceAll("^\"|\"$", "");
            map.put(k, v);
        }
        return map;
    }
}
