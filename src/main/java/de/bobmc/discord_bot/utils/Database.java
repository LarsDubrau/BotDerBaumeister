package de.bobmc.discord_bot.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import de.bobmc.discord_bot.model.UserActivity;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void insertNewActivity(UserActivity activity) {
        MongoCollection activities = client.getDatabase("stats").getCollection("activities");
        Document activityDoc = new Document()
                .append("userId", activity.getUserId())
                .append("username", activity.getUsername())
                .append("dateStart", activity.getDateBegin());
        activities.insertOne(activityDoc);
    }

    public void endActivity(String userId) {
        MongoCollection activities = client.getDatabase("stats").getCollection("activities");
        Document filterOpenActivities = new Document()
                .append("userId", userId)
                .append("dateEnd", null);
        Document newDocument = new Document().append("dateEnd", new Date());
        Document updateDocument = new Document().append("$set", newDocument);
        activities.updateOne(filterOpenActivities, updateDocument);
    }

    public List<UserActivity> getActivities(){
        List<UserActivity> resultList = new ArrayList<>();

        MongoCollection activities = client.getDatabase("stats").getCollection("activities");
        for(Object o : activities.find()){
            Document entry =  (Document) o;
            resultList.add(UserActivity.fromDocument(entry));
        }

        return resultList;
    }
}
