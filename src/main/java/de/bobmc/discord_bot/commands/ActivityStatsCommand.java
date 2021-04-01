package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.apis.TenorGifApi;
import de.bobmc.discord_bot.model.UserActivity;
import de.bobmc.discord_bot.utils.Database;
import de.bobmc.discord_bot.utils.DateUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityStatsCommand implements Command{

    @Override
    public void run(String[] args, TextChannel channel, Member member) {
        List<UserActivity> activities = Database.getInstance().getActivities();

        //calculate total time in seconds for each user
        Map<String, Long> times = new HashMap<>();
        for(UserActivity activity : activities){
            String username = activity.getUsername();
            long time = activity.getDurationInSeconds();
            if(times.containsKey(username)){
                long newValue = times.get(username) + time;
                times.replace(username, newValue);
            } else {
                times.put(username, time);
            }
        }

        //print values
        EmbedBuilder builder= new EmbedBuilder();
        builder.setTitle("Statistik");
        builder.setDescription("Insgesamt verbrachte Zeit im Voicechannel");

        for(String key : times.keySet()){
            builder.addField(key, DateUtils.convertDurationToString(times.get(key)), false);
        }

        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getIdentifier() {
        return "!stats";
    }

    @Override
    public String getUsage() {
        return "Zeigt an, wer wie viele Stunden in Sprachkanälen verbracht hat";
    }
}
