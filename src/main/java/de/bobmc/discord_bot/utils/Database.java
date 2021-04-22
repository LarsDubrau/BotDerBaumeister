package de.bobmc.discord_bot.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import de.bobmc.discord_bot.model.DropRate;
import de.bobmc.discord_bot.model.VoiceChannelTime;
import org.bson.Document;

import java.util.*;
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

    private MongoCollection getCollection(String collectionName){
        return client.getDatabase("BobMC").getCollection(collectionName);
    }

    public List<DropRate> getFirstThreeHighest(){
        List<DropRate> result = new ArrayList<>();

        MongoCollection collection = getCollection("dropRecord");
        Document sort  = new Document().append("dropRate", -1);
        collection.find().sort(sort).limit(3).forEach((Consumer) o -> {
            Document doc = (Document) o;
            result.add(new DropRate(doc.getString("username"), doc.getDouble("dropRate")));
        });
        return result;
    }

    public void startVoiceChannelSession(String username){
        MongoCollection collection = getCollection("voiceChannelTime");
        Document doc = new Document()
                .append("username", username)
                .append("start", new Date())
                .append("end", null);
        collection.insertOne(doc);
    }

    public void stopVoiceChannelSession(String username){
        MongoCollection collection = getCollection("voiceChannelTime");
        Document filter = new Document()
                .append("username", username)
                .append("end", null);
        Document update = new Document()
                .append("$set", new Document().append("end", new Date()));
        collection.updateOne(
                filter,
                update
        );
    }

    public Map<String, VoiceChannelTime> getVoiceChannelTimes(){
        Map<String,  VoiceChannelTime> result = new HashMap<>();
        MongoCollection collection = getCollection("voiceChannelTime");
        collection.find().forEach((Consumer) o -> {
            Document doc = (Document) o;
            String username = doc.getString("username");
            long time = doc.getDate("end").getTime() - doc.getDate("start").getTime();
            time = time / 1000; //convert to seconds
            if(result.containsKey(username)){
                result.get(username).addTime(time);
            } else {
                result.put(username, new VoiceChannelTime(username, time));
            }
        });
        return result;
    }
}
