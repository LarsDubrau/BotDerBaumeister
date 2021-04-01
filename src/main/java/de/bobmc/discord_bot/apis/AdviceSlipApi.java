package de.bobmc.discord_bot.apis;

import de.bobmc.discord_bot.utils.ApiRequestHelper;
import org.json.JSONObject;

import java.io.IOException;

public class AdviceSlipApi {
    private static String baseUrl = "https://api.adviceslip.com/";

    public static String getRandomAdvice(){
        String url = baseUrl + "advice";
        try{
            JSONObject response = ApiRequestHelper.makeGetRequest(url);
            return response.getJSONObject("slip").getString("advice");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
