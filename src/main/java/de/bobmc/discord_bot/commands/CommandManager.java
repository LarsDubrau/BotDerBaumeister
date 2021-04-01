package de.bobmc.discord_bot.commands;

import de.bobmc.discord_bot.utils.Logging;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager extends ListenerAdapter {
    private Map<String, Command> commands;

    public CommandManager() {
        this.commands = new HashMap<>();
    }

    public void addCommand(Command command) {
        commands.put(command.getIdentifier(), command);
    }

    private void printUsages(TextChannel channel) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Befehle");
        for (String key : commands.keySet()) {
            Command cmd = commands.get(key);
            String usage = cmd.getUsage();
            if (usage == null) {
                usage = "Keine Beschreibung vorhanden";
            }
            eb.addField(cmd.getIdentifier(), usage, false);
        }
        channel.sendMessage(eb.build()).queue();
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.startsWith("!")) {
            String[] args = message.split(" ");
            TextChannel channel = event.getChannel();
            Member member = event.getMember();
            Logging.getInstance().logInfo("Command received: \n" +
                    "Args: " + Arrays.asList(args).toString() + "\n" +
                    "Channel: " + channel.getName() + "\n" +
                    "Member: " + member.getEffectiveName());
            if (commands.containsKey(args[0])) {
                commands.get(args[0]).run(args, channel, member);
            }
            if (args[0].equals("!help")) {
                printUsages(channel);
            }
        }
    }
}
