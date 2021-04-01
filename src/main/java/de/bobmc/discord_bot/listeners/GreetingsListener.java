package de.bobmc.discord_bot.listeners;

import de.bobmc.discord_bot.apis.TenorGifApi;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class GreetingsListener extends ListenerAdapter {
    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        String gifUrl = TenorGifApi.getRandomGifByQuery("bob the builder");

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Hallo " + event.getMember().getEffectiveName());
        builder.setImage(gifUrl);

        event.getMember().getDefaultChannel().sendMessage(builder.build())
                .queue(m -> m.delete().queueAfter(10, TimeUnit.SECONDS));
    }
}
