package de.bobmc.discord_bot.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
    private Logger logger;

    private static Logging INSTANCE;

    private Logging(){
        try {
            logger = Logger.getLogger("Discord Bot");
            FileHandler fh = new FileHandler(ConfigManager.getInstance().getString("logFilePath"));
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logging getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Logging();
        }
        return INSTANCE;
    }

    public void logInfo(String msg){
        logger.info(msg);
    }
}
