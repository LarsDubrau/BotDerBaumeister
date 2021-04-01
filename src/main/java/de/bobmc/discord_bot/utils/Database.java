package de.bobmc.discord_bot.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Database {
    private MongoClient client;

    private static Database INSTANCE;

    private Database() {
        this.client = MongoClients.create(
                ConfigManager.getInstance().getString("mongoDbConnectionString"));
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }
}
