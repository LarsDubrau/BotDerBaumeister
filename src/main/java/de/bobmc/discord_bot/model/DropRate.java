package de.bobmc.discord_bot.model;

public class DropRate {
    String username;
    double dropRate;

    public DropRate(String username, double dropRate) {
        this.username = username;
        this.dropRate = dropRate;
    }

    public String getUsername() {
        return username;
    }

    public double getDropRate() {
        return dropRate;
    }
}
