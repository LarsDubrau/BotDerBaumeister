package de.bobmc.discord_bot.model;

import org.bson.Document;

import java.util.Date;

public class UserActivity {
    private String userId;
    private String username;
    private Date dateBegin;
    private Date dateEnd;

    public UserActivity(String userId, String username, Date dateBegin, Date dateEnd) {
        this.userId = userId;
        this.username = username;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public static UserActivity startNewActivity(String userId, String username){
        return new UserActivity(userId, username, new Date(), null);
    }

    public static UserActivity fromDocument(Document doc){
        return new UserActivity(
                doc.getString("userId"),
                doc.getString("username"),
                doc.getDate("dateStart"),
                doc.getDate("dateEnd")
        );
    }

    public long getDurationInSeconds(){
        if(dateEnd == null){
            return (new Date().getTime() - dateBegin.getTime()) / 1000;
        }
        return (dateEnd.getTime() - dateBegin.getTime()) / 1000;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
