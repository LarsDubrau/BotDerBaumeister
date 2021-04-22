package de.bobmc.discord_bot.listeners;

import de.bobmc.discord_bot.utils.Database;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class VoiceChannelSessionsListener extends ListenerAdapter {
    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        Database.getInstance().startVoiceChannelSession(event.getMember().getEffectiveName());
    }

    @Override
    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {
        Database.getInstance().stopVoiceChannelSession(event.getMember().getEffectiveName());
    }
}
