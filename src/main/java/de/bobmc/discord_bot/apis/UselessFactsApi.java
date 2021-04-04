package de.bobmc.discord_bot.apis;

import de.bobmc.discord_bot.model.UselessFact;
import de.bobmc.discord_bot.utils.ApiRequestHelper;
import org.json.JSONObject;

import java.io.IOException;

public class UselessFactsApi {
    private static String baseUrl = "https://uselessfacts.jsph.pl/";

    public static UselessFact getRandomUselessFact(){
        try {
            JSONObject response = ApiRequestHelper.makeGetRequest(baseUrl + "random.json?language=de");
            return UselessFact.fromJson(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
