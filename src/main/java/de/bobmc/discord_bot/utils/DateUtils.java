package de.bobmc.discord_bot.utils;

public class DateUtils {
    public static String convertDurationToString(long seconds){
        if(seconds > 3600){
            return seconds / 3600 + " Stunde(n)";
        } else  if(seconds > 60){
            return seconds / 60 + " Minute(n)";
        } else {
            return seconds +  " Sekunde(n)";
        }
    }
}
