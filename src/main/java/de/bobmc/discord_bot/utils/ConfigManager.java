package de.bobmc.discord_bot.utils;

import org.json.JSONObject;

import java.io.*;

public class ConfigManager {
    private JSONObject config;
    private static ConfigManager INSTANCE;

    private ConfigManager(JSONObject config) {
        this.config = config;
    }

    public static ConfigManager getInstance() {
        if (INSTANCE == null) {
            throw new IllegalAccessError("You have to call readConfig before getting a instance.");
        }
        return INSTANCE;
    }

    public String getString(String key){
        return config.getString(key);
    }

    public static void readConfig(String path) {
        File configFile = new File(path);
        if(!configFile.isFile()){
            throw new IllegalArgumentException("Given config file does not exist: " + configFile.getAbsolutePath());
        }

        JSONObject jsonObject = new JSONObject(readFile(configFile));
        INSTANCE = new ConfigManager(jsonObject);
    }

    private static String readFile(File file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String content = "";
            String line;
            while((line = br.readLine()) != null){
                content += line + "\n";
            }
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
