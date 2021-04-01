package de.bobmc.discord_bot.model;

import org.json.JSONObject;

public class R6UserStats {
    private String username;
    private String avatarUrl;
    private int level;
    private int timePlayed;

    private int wins;
    private int losses;

    private int kills;
    private double kd;
    private int headshots;
    private int penetrationKills;
    private int suicides;
    private int gadgetsDestroyed;

    public static R6UserStats fromJson(JSONObject json){
        System.out.println("Create user stats from json: \n" + json.toString(3));
        JSONObject stats = json.getJSONObject("stats").getJSONObject("general");

        R6UserStatsBuilder builder = R6UserStatsBuilder.aR6UserStats()
                .withUsername(json.getString("username"))
                .withAvatarUrl(json.getString("avatar_url_146"))
                .withLevel(json.getJSONObject("progression").getInt("level"))
                .withTimePlayed(stats.getInt("playtime"))
                .withWins(stats.getInt("wins"))
                .withLosses(stats.getInt("losses"))
                .withKills(stats.getInt("kills"))
                .withKd(stats.getDouble("kd"))
                .withHeadshots(stats.getInt("headshots"))
                .withPenetrationKills(stats.getInt("penetration_kills"))
                .withSuicides(stats.getInt("suicides"))
                .withGadgetsDestroyed(stats.getInt("gadgets_destroyed"));
        return builder.build();
    }

    public double getWinRate(){
        return (double) getWins() / ((double) getLosses() + (double) getWins());
    }

    public double getHeadshotRate(){
        return (double) getHeadshots() / (double) getKills();
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getLevel() {
        return level;
    }

    public int getTimePlayed() {
        //Umrechnung von Sekunden in Stunden
        return timePlayed / 3600;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getKills() {
        return kills;
    }

    public double getKd() {
        System.out.println("kd is " + kd);
        return kd;
    }

    public int getHeadshots() {
        return headshots;
    }

    public int getPenetrationKills() {
        return penetrationKills;
    }

    public int getSuicides() {
        return suicides;
    }

    public int getGadgetsDestroyed() {
        return gadgetsDestroyed;
    }

    public static final class R6UserStatsBuilder {
        private String username;
        private String avatarUrl;
        private int level;
        private int timePlayed;
        private int wins;
        private int losses;
        private int kills;
        private double kd;
        private int headshots;
        private int penetrationKills;
        private int suicides;
        private int gadgetsDestroyed;

        private R6UserStatsBuilder() {
        }

        public static R6UserStatsBuilder aR6UserStats() {
            return new R6UserStatsBuilder();
        }

        public R6UserStatsBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public R6UserStatsBuilder withAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public R6UserStatsBuilder withLevel(int level) {
            this.level = level;
            return this;
        }

        public R6UserStatsBuilder withTimePlayed(int timePlayed) {
            this.timePlayed = timePlayed;
            return this;
        }

        public R6UserStatsBuilder withWins(int wins) {
            this.wins = wins;
            return this;
        }

        public R6UserStatsBuilder withLosses(int losses) {
            this.losses = losses;
            return this;
        }

        public R6UserStatsBuilder withKills(int kills) {
            this.kills = kills;
            return this;
        }

        public R6UserStatsBuilder withKd(double kd) {
            this.kd = kd;
            return this;
        }

        public R6UserStatsBuilder withHeadshots(int headshots) {
            this.headshots = headshots;
            return this;
        }

        public R6UserStatsBuilder withPenetrationKills(int penetrationKills) {
            this.penetrationKills = penetrationKills;
            return this;
        }

        public R6UserStatsBuilder withSuicides(int suicides) {
            this.suicides = suicides;
            return this;
        }

        public R6UserStatsBuilder withGadgetsDestroyed(int gadgetsDestroyed) {
            this.gadgetsDestroyed = gadgetsDestroyed;
            return this;
        }

        public R6UserStats build() {
            R6UserStats r6UserStats = new R6UserStats();
            r6UserStats.kd = this.kd;
            r6UserStats.timePlayed = this.timePlayed;
            r6UserStats.wins = this.wins;
            r6UserStats.penetrationKills = this.penetrationKills;
            r6UserStats.username = this.username;
            r6UserStats.headshots = this.headshots;
            r6UserStats.suicides = this.suicides;
            r6UserStats.avatarUrl = this.avatarUrl;
            r6UserStats.level = this.level;
            r6UserStats.gadgetsDestroyed = this.gadgetsDestroyed;
            r6UserStats.kills = this.kills;
            r6UserStats.losses = this.losses;
            return r6UserStats;
        }
    }
}
