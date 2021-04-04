package de.bobmc.discord_bot.model;

import org.json.JSONObject;

public class UselessFact {
    private String fact;
    private String source;
    private String url;

    public UselessFact(String fact, String source, String url) {
        this.fact = fact;
        this.source = source;
        this.url = url;
    }

    public static UselessFact fromJson(JSONObject json){
        return new UselessFact(
                json.getString("text"),
                json.getString("source"),
                json.getString("source_url")
        );
    }

    public String getFact() {
        return fact;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }
}
