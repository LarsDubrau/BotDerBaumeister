package de.bobmc.discord_bot.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import de.bobmc.discord_bot.model.DropRate;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    public List<DropRate> getFirstThreeHighest(){
        List<DropRate> result = new ArrayList<>();

        MongoCollection collection = client.getDatabase("BobMC").getCollection("drop_rates");
        Document sort  = new Document().append("dropRate", -1);
        collection.find().sort(sort).limit(3).forEach((Consumer) o -> {
            Document doc = (Document) o;
            result.add(new DropRate(doc.getString("username"), doc.getDouble("dropRate")));
        });
        return result;
    }
}
