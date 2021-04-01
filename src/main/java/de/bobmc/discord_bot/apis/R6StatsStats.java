package de.bobmc.discord_bot.apis;

import de.bobmc.discord_bot.utils.ApiRequestHelper;
import de.bobmc.discord_bot.utils.ConfigManager;
import org.json.JSONObject;

import java.io.IOException;

public class R6StatsStats {
    private static String baseUrl = "https://api2.r6stats.com/public-api/";

    public static JSONObject getPlayerStats(String username){
        String key = ConfigManager.getInstance().getString("r6StatsApiToken");
        String url = baseUrl + "stats/" + username + "/pc/generic";
        try {
            JSONObject response = ApiRequestHelper.makeGetRequestWithBearerToken(url, key);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
