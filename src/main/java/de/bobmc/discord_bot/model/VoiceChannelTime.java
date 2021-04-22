package de.bobmc.discord_bot.model;

public class VoiceChannelTime {
    String username;
    long voiceChannelTime;

    public VoiceChannelTime(String username, long voiceChannelTime) {
        this.username = username;
        this.voiceChannelTime = voiceChannelTime;
    }

    public String getUsername() {
        return username;
    }

    public long getVoiceChannelTime() {
        return voiceChannelTime;
    }

    public void addTime(long time){
        this.voiceChannelTime += time;
    }
}
