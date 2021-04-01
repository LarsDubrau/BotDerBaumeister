package de.bobmc.discord_bot.listeners;

import de.bobmc.discord_bot.model.UserActivity;
import de.bobmc.discord_bot.utils.Database;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ActivityStatsListener extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        String userId = event.getMember().getId();
        String username = event.getMember().getEffectiveName();
        UserActivity activity = UserActivity.startNewActivity(userId, username);
        Database.getInstance().insertNewActivity(activity);
    }

    @Override
    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {
        String userId = event.getMember().getId();
        Database.getInstance().endActivity(userId);
    }
}
