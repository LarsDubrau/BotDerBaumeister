package de.bobmc.discord_bot.apis;

import de.bobmc.discord_bot.utils.ApiRequestHelper;
import de.bobmc.discord_bot.utils.ConfigManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class TenorGifApi {
    private static String baseUrl = "https://g.tenor.com/v1/";

    public static String getRandomGifByQuery(String keyword) {
        String token = ConfigManager.getInstance().getString("tenorApiToken");
        String url = baseUrl + "random?key=" + token + "&q=" + keyword;
        try {
            JSONObject jsonObject = ApiRequestHelper.makeGetRequest(url);
            JSONArray gifs = jsonObject.getJSONArray("results");
            if(gifs.length() == 0){
                return null;
            }
            JSONObject gif = gifs.getJSONObject(new Random().nextInt(gifs.length()));
            return gif.getJSONArray("media").getJSONObject(0).getJSONObject("tinygif").getString("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
