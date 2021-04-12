package de.bobmc.discord_bot;

import de.bobmc.discord_bot.commands.*;
import de.bobmc.discord_bot.listeners.GreetingsListener;
import de.bobmc.discord_bot.utils.ConfigManager;
import de.bobmc.discord_bot.utils.Logging;
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

            Logging.getInstance().logInfo("Discord Bot wurde gestartet");

            //create command manager and add supported commands
            CommandManager commandManager = new CommandManager();
            //commandManager.addCommand(new ActivityStatsCommand());
            commandManager.addCommand(new AdviceCommand());
            commandManager.addCommand(new R6StatsCommand());
            commandManager.addCommand(new UselessFactCommand());
            commandManager.addCommand(new DropRateCommand());

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
