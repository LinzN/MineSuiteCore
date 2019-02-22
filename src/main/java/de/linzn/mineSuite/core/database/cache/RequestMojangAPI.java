package de.linzn.mineSuite.core.database.cache;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class RequestMojangAPI {
    public static String fetchName(UUID uuid) {
        JSONArray jsonArray = (JSONArray) getJSON("https://api.mojang.com/user/profiles/" + convertDefaultUUIDToTrim(uuid) + "/names");
        if (jsonArray == null) {
            return null;
        }
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);

        if (jsonObject == null) {
            return null;
        }

        return (String) jsonObject.get("name");
    }

    public static UUID fetchUUID(String playerName) {
        JSONObject jsonObject = (JSONObject) getJSON("https://api.mojang.com/users/profiles/minecraft/" + playerName);
        if (jsonObject == null) {
            return null;
        }
        String uuidAsString = (String) jsonObject.get("id");

        if (uuidAsString == null) {
            return null;
        }

        return convertTrimUUIDToDefault(uuidAsString);
    }

    private static Object getJSON(String sURL) {
        JSONParser parser = new JSONParser();
        try {
            URL uri = new URL(sURL);
            URLConnection ec = uri.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    ec.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder a = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                a.append(inputLine);
            in.close();
            return parser.parse(a.toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static UUID convertTrimUUIDToDefault(String uuid) {
        return UUID.fromString(uuid.length() == 32 ? uuid.substring(0, 8) + '-' + uuid.substring(8, 12) + '-' + uuid.substring(12, 16) + '-' + uuid.substring(16, 20) + '-' + uuid.substring(20) : uuid);
    }

    private static String convertDefaultUUIDToTrim(UUID uuid) {
        return uuid.toString().replace("-", "");
    }
}
