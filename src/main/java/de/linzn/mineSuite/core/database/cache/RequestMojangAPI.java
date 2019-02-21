package de.linzn.mineSuite.core.database.cache;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class RequestMojangAPI {
    public String fetchName(UUID uuid) {
        JSONObject jsonObject = (JSONObject) getJSONArray("https://api.mojang.com/user/profiles/" + uuid.toString() + "/names").get(1);
        return (String) jsonObject.get("name");
    }

    public UUID fetchUUID(String playerName) {
        return UUID.fromString((String) getJSONObject("https://api.mojang.com/users/profiles/minecraft/" + playerName).get("id"));
    }

    private static JSONObject getJSONObject(String sURL) {
        JSONObject obj = null;
        try {
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();
            obj = (JSONObject) new JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (ParseException | IOException ignored) {
        }
        return obj;
    }

    private static JSONArray getJSONArray(String sURL) {
        JSONArray arr = null;
        try {
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();
            arr = (JSONArray) new JSONParser().parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (ParseException | IOException ignored) {
        }
        return arr;
    }
}
