package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.apis.TenorGifApi;
import de.bobmc.discord_bot.model.DropRate;
import de.bobmc.discord_bot.utils.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class DropRateCommand implements Command {
    @Override
    public void run(String[] args, TextChannel channel, Member member) {
        List<DropRate> highest = Database.getInstance().getFirstThreeHighest();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Höchste Pack Wahrscheinlichkeit aller Zeiten");
        builder.setImage(TenorGifApi.getRandomGifByQuery("winner"));
        for (int i = 0; i < highest.size(); i++) {
            DropRate rate = highest.get(i);
            builder.addField(
                    rate.getUsername() + " (" + rate.getDropRate() + "%)",
                    (i + 1) + ". Platz",
                    false
            );
        }
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getIdentifier() {
        return "!chance";
    }

    @Override
    public String getUsage() {
        return "Zeigt, wer die höchste Pack-Wahrscheinlichkeit seit Beginn " +
                "der Aufzeichnung (14.04.2021) hatte";
    }
}
