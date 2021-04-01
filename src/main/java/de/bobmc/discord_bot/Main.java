package de.bobmc.discord_bot;

import de.bobmc.discord_bot.commands.ActivityStatsCommand;
import de.bobmc.discord_bot.commands.AdviceCommand;
import de.bobmc.discord_bot.commands.CommandManager;
import de.bobmc.discord_bot.commands.R6StatsCommand;
import de.bobmc.discord_bot.listeners.ActivityStatsListener;
import de.bobmc.discord_bot.listeners.GreetingsListener;
import de.bobmc.discord_bot.utils.ConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {
        if(args.length < 1){
            throw new IllegalArgumentException("No config file path given");
        }
        ConfigManager.readConfig(args[0]);
        try {
            JDABuilder builder = JDABuilder.createDefault(ConfigManager.getInstance().getString("botToken"));
            JDA jda = builder.build();
            jda.awaitReady();

            //create command manager and add supported commands
            CommandManager commandManager = new CommandManager();
            //commandManager.addCommand(new ActivityStatsCommand());
            commandManager.addCommand(new AdviceCommand());
            commandManager.addCommand(new R6StatsCommand());

            //add listeners
            jda.addEventListener(commandManager);
            jda.addEventListener(new GreetingsListener());
            //jda.addEventListener(new ActivityStatsListener());
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
