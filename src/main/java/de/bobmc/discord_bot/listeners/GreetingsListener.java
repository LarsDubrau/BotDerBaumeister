package de.bobmc.discord_bot.listeners;

import de.bobmc.discord_bot.apis.TenorGifApi;
import de.bobmc.discord_bot.utils.Logging;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class GreetingsListener extends ListenerAdapter {
    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        //not sedning gif to bots joining the channel
        if(event.getMember().getUser().isBot()){
            return;
        }

        Logging.getInstance().logInfo("Loading welcome give for " +
                event.getMember().getEffectiveName() +
                " in channel " + event.getChannelJoined().getName());
        String gifUrl = TenorGifApi.getRandomGifByQuery("bob the builder");

        //troll
        if(event.getMember().getEffectiveName().equals("venti")){
            gifUrl = TenorGifApi.getRandomGifByQuery("fuck off");
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Hallo " + event.getMember().getEffectiveName());
        builder.setImage(gifUrl);

        event.getMember().getGuild().getDefaultChannel().sendMessage(builder.build()).queue(
                m -> m.delete().queueAfter(10, TimeUnit.SECONDS)
        );
    }
}
