package me.codeblooded.timeparkour.plugin.game.stats;

import java.util.HashMap;
import java.util.UUID;

public class Profile {

    private UUID player;

    private HashMap<String, MapProfile> mapstats = new HashMap<String, MapProfile>();

    public Profile(UUID player) {
        this.player = player;
    }

    public UUID getPlayer() {
        return player;
    }

    public HashMap<String, MapProfile> getMapstats() {
        return mapstats;
    }
}
