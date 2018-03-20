package com.flowedu.util;

import com.amazonaws.util.json.JSONException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jihoan on 2017. 8. 9..
 */
public class GsonJsonUtil {

    /**
     * URL GET방식 json 파싱
     * @param surl
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static JsonObject readJsonFromUrl(String surl) throws IOException, JSONException {
        URL url = new URL(surl);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootObj = root.getAsJsonObject();
        return rootObj;
    }

    public static JsonArray readJsonFromJsonString(String jsonStr) {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonStr);
        JsonArray jsonArray = element.getAsJsonArray();
        return jsonArray;
    }

    /**
     * jsonString으로 만들기
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static<T> String convertToJsonString(T t) throws Exception {
        return new ObjectMapper().writeValueAsString(t);
    }
}
