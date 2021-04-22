package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.model.VoiceChannelTime;
import de.bobmc.discord_bot.utils.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Map;

public class VoiceTimeCommand implements Command{
    @Override
    public void run(String[] args, TextChannel channel, Member member) {
        Map<String, VoiceChannelTime> times = Database.getInstance().getVoiceChannelTimes();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Voice Channel Zeiten");
        for(String username : times.keySet()){
            double timeInH = (double) times.get(username).getVoiceChannelTime() / 3600.0;
            builder.addField(username, timeInH + "h", false);
        }
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getIdentifier() {
        return "!time";
    }

    @Override
    public String getUsage() {
        return "Zeigt, wer wie lange in Voice Channels war.";
    }
}
