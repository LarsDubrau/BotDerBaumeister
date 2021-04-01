package de.bobmc.discord_bot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public interface Command {
    void run(String[] args, TextChannel channel, Member member);
    String getIdentifier();
    String getUsage();
}
