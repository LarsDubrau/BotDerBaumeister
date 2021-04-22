package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.apis.TenorGifApi;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class GifCommand implements Command{
    @Override
    public void run(String[] args, TextChannel channel, Member member) {
        if(args.length < 2){
            channel.sendMessage("Gib einen Suchbegriff ein").queue();
            return;
        }
        String q = "";
        for(int i = 1; i < args.length; i++){
            q += args[i]+ " ";
        }
        String gifUrl = TenorGifApi.getRandomGifByQuery(q);
        if(gifUrl == null){
            channel.sendMessage("Kein gif gefunden").queue();
            return;
        }
        EmbedBuilder builder = new EmbedBuilder();
        builder.setImage(gifUrl);
        builder.setFooter(q);
        channel.sendMessage(builder.build()).queue();
    }

    @Override
    public String getIdentifier() {
        return "!gif";
    }

    @Override
    public String getUsage() {
        return "Zeigt dir ein gif mit dem eingegebenenm Thema";
    }
}
