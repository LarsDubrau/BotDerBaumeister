package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.apis.UselessFactsApi;
import de.bobmc.discord_bot.model.UselessFact;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class UselessFactCommand implements Command{
    @Override
    public void run(String[] args, TextChannel channel, Member member) {
        UselessFact fact = UselessFactsApi.getRandomUselessFact();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Unnützes Wissen");
        builder.setDescription(fact.getFact());
        builder.setFooter("Quelle: " + fact.getSource() + " (" + fact.getUrl() + ")");
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getIdentifier() {
        return "!fact";
    }

    @Override
    public String getUsage() {
        return "Gibt dir interessante Fakten, die dir nichts bringen.";
    }
}
