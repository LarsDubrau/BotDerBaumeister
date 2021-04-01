package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.apis.R6StatsStats;
import de.bobmc.discord_bot.model.R6UserStats;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;

public class R6StatsCommand implements Command{
    @Override
    public void run(String[] args, TextChannel channel, Member member) {
        if(args.length < 2){
            channel.sendMessage("Gebe einen Nutzernamen ein um die Stats zu erhalten.").queue();
            return;
        }
        String username = args[1];
        R6UserStats response = R6StatsStats.getPlayerStats(username);

        if(response == null){
            channel.sendMessage("Der Spieler wurde nicht gefunden").queue();
            return;
        }

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(args[1]);
        builder.setImage(response.getAvatarUrl());
        builder.setDescription("Level " + response.getLevel());
        builder.addField("Wins insgesamt", String.valueOf(response.getWins()), true);
        builder.addField("Kills insgesamt", String.valueOf(response.getKills()), true);
        builder.addField("Winrate", df.format(response.getWinRate() * 100) + "%", true);
        builder.addField("Headshotrate", df.format(response.getHeadshotRate() * 100) + "%", true);
        builder.addField("Penetrationkills", String.valueOf(response.getPenetrationKills()), true);
        builder.addField("Zerstörte Gadgets", String.valueOf(response.getGadgetsDestroyed()), true);
        builder.addField("Spielzeit", response.getTimePlayed() + " Stunden", true);
        builder.addField("Selbstmord", String.valueOf(response.getSuicides()), true);
        builder.addField("K/D", String.valueOf(response.getKd()), true);
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
