package me.codeblooded.timeparkour.plugin.game.stats;

import me.codeblooded.timeparkour.plugin.utils.DBConnector;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StatsManager {

    private static StatsManager instance = new StatsManager();

    protected DBConnector connector;

    public static StatsManager getInstance() {
        return instance;
    }

    public void initialize() {
        this.connector = new DBConnector("", "", "", "");
    }

    /*
     *   Returns if database entry was successful
     *   UUID - player uuid
     *   Name - player name
     *   First - first recorded time
     *   Last - last time recorded
     *   Record - fastest time
     *   Attempts - total times complete
     */
    public boolean createMapEntry(String map) {
        String statement = "CREATE TABLE IF NOT EXISTS '" + map + "' (uuid VARCHAR(36), name VARCHAR(16), first INT, last INT, record INT, attempts INT)";

        PreparedStatement preparedStatement = null;

        boolean successful = false;

        try {
            preparedStatement = connector.getConnection().prepareStatement(statement);

            preparedStatement.execute();

            successful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return successful;
    }

    /*
     *   Returns if database entry was successful
     */
    public boolean updatePlayerEntry(Player p, String map, int time) {
        String statement = null;

        PreparedStatement preparedStatement = null;

        boolean successful = false;

        try {
            if(connector.contains(map, "uuid", p.getUniqueId().toString())) {
                // TODO Generate statement
            } else {
                // TODO Generate statement
            }

            preparedStatement = connector.getConnection().prepareStatement(statement);

            preparedStatement.execute();

            successful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return successful;
    }

    /*
     *   Returns players position on a maps scoreboard
     */
    public int getEntryPosition(Player p, String map) {
        int position = 0;

        String statement = "SELECT * FROM (SELECT @position:=@position+1 AS position, uuid, name, first, last, record, attempts, (SELECT @position := 0) r ORDER BY record DESC) t WHERE uuid='" + p.getUniqueId().toString() + "'";

        PreparedStatement preparedStatement = null;

        ResultSet result = null;

        try {
            if(connector.contains(map, "uuid", p.getUniqueId().toString())) {
                preparedStatement = connector.getConnection().prepareStatement(statement);

                result = preparedStatement.executeQuery();

                result.next();

                position = result.getInt("position");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

                if(result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return position;
    }

    /*
     *   Returns players position on a maps scoreboard
     *   List<Object> contains player time and position
     */
    public HashMap<UUID, List<Object>> getTopEntries(String map) {
        HashMap<UUID, List<Object>> scores = new HashMap<UUID, List<Object>>();

        String statement = "SELECT * FROM (SELECT @position:=@position+1 AS position, uuid, name, first, last, record, attempts, (SELECT @position := 0) r ORDER BY record DESC) t LIMIT 5";

        PreparedStatement preparedStatement = null;

        ResultSet results = null;

        try {
            preparedStatement = connector.getConnection().prepareStatement(statement);

            results = preparedStatement.executeQuery();

            while(results.next()) {
                List<Object> objects = new ArrayList<Object>();

                objects.add(results.getInt("position"));

                objects.add(results.getInt("record"));

                scores.put(UUID.fromString(results.getString("uuid")), objects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

                if(results != null)
                    results.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return scores;
    }
}