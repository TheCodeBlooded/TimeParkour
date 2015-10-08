package me.codeblooded.timeparkour.plugin.game;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GameManager {

    private static GameManager instance = new GameManager();

    private HashMap<UUID, Game> games = new HashMap<UUID, Game>();

    public static GameManager getInstance() {
        return instance;
    }

    public void createGame(Player p, String map) {
        if(games.containsKey(p.getUniqueId()))
            removeGame(p);

        Game game = new Game(p.getUniqueId(), map);

        game.start();

        games.put(p.getUniqueId(), game);
    }

    public void removeGame(Player p) {
        if(games.containsKey(p.getUniqueId())) {
            games.get(p.getUniqueId()).forceEnd();

            games.get(p.getUniqueId()).destroy();

            games.remove(p.getUniqueId());
        }
    }

    public HashMap<UUID, Game> getGames() {
        return games;
    }
}
