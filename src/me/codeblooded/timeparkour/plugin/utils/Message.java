package me.codeblooded.timeparkour.plugin.utils;

import org.bukkit.ChatColor;

public enum Message {
    ERROR("&4Error&8> &r%message%"), SUCCESS("&4Success&8> &r%message%");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', this.message.replace("%message%", message));
    }
}
