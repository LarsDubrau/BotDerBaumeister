package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.apis.R6StatsStats;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class R6StatsCommand implements Command{
    @Override
    public void run(String[] args, TextChannel channel, Member member) {
        if(args.length < 2){
            channel.sendMessage("Gebe einen Nutzernamen ein um die Stats zu erhalten.").queue();
            return;
        }
        String username = args[1];
        JSONObject response = R6StatsStats.getPlayerStats(username);

        if(response == null){
            channel.sendMessage("Der Spieler wurde nicht gefunden").queue();
            return;
        }

        //get level
        int level = response.getJSONObject("progression").getInt("level");

        //get stats
        JSONObject stats = response.getJSONObject("stats").getJSONObject("general");
        int kills = stats.getInt("kills");
        int wins = stats.getInt("wins");
        int losses = stats.getInt("losses");
        int headshots = stats.getInt("headshots");
        int penetrationsKills = stats.getInt("penetration_kills");
        int gadgetsDestroyed = stats.getInt("gadgets_destroyed");
        int timePlayed = stats.getInt("playtime") / 3600;
        String avatarUrl = response.getString("avatar_url_146");
        double kd = stats.getDouble("kd");
        int suicides = stats.getInt("suicides");
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        double winrate = (double) wins / ((double) wins + losses);
        double headshotRate = (double) headshots / (double) kills;


        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(args[1]);
        builder.setImage(avatarUrl);
        builder.setDescription("Level " + level);
        builder.addField("Wins insgesamt", String.valueOf(wins), true);
        builder.addField("Kills insgesamt", String.valueOf(kills), true);
        builder.addField("Winrate", df.format(winrate * 100) + "%", true);
        builder.addField("Headshotrate", df.format(headshotRate * 100) + "%", true);
        builder.addField("Penetrationkills", String.valueOf(penetrationsKills), true);
        builder.addField("Zerstörte Gadgets", String.valueOf(gadgetsDestroyed), true);
        builder.addField("Spielzeit", timePlayed + " Stunden", true);
        builder.addField("Selbstmord", String.valueOf(suicides), true);
        builder.addField("K/D", String.valueOf(kd), true);
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getIdentifier() {
        return "!r6";
    }

    @Override
    public String getUsage() {
        return "Zeigt die Rainbow Six Siege Statistiken eines Spielers";
    }
}
