package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.apis.AdviceSlipApi;
import de.bobmc.discord_bot.utils.Logging;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class AdviceCommand implements Command{
    @Override
    public void run(String[] args, TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Weisheit des Tages");
        builder.setDescription(AdviceSlipApi.getRandomAdvice());
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getIdentifier() {
        return "!advice";
    }

    @Override
    public String getUsage() {
        return "Gibt dir einen guten Rat";
    }
}
