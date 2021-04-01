package de.bobmc.discord_bot.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequestHelper {
    public static JSONObject makeGetRequest(String url) throws IOException {
        //make request
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");

        if(connection.getResponseCode() != 200){
            return null;
        }

        //read result
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer content = new StringBuffer();
        String line;
        while((line = br.readLine()) != null){
            content.append(line);
        }
        return new JSONObject(content.toString());
    }

    public static JSONObject makeGetRequestWithBearerToken(String url, String token) throws IOException {
        //make request
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");

        //set token
        connection.setRequestProperty("Authorization", "Bearer " + token);

        if(connection.getResponseCode() != 200){
            return null;
        }

        //read result
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer content = new StringBuffer();
        String line;
        while((line = br.readLine()) != null){
            content.append(line);
        }
        return new JSONObject(content.toString());
    }
}
