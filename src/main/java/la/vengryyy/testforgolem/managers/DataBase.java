package la.vengryyy.testforgolem.managers;

import la.vengryyy.testforgolem.Main;

import java.sql.*;

public class DataBase{

    public static Connection co = null;
    private final Main plugin;

    public DataBase(Main plugin) {
        this.plugin = plugin;
    }

    public void connectToDB() {
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:" + this.plugin.getDataFolder().getAbsolutePath() + "/database.db");

            DatabaseMetaData md = co.getMetaData();
            ResultSet players = md.getTables(null, null, "players", null);

            if(!players.next()) {
                stmt = co.createStatement();
                String sql = "CREATE TABLE players " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " player TEXT NOT NULL," +
                        " COCQ INTEGER NOT NULL," +
                        " performedID INTEGER NOT NULL )";
                stmt.executeUpdate(sql);
                stmt.close();
            }

            players.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void closeConnection() throws SQLException {
        co.close();
    }

    public boolean playerExists(String player) {
        boolean isPlayerExists = false;
        try (PreparedStatement statement = co.prepareStatement("SELECT * FROM players WHERE player = ?")) {
            statement.setObject(1, player);
            try (ResultSet rs = statement.executeQuery()) {if (rs.next()) {isPlayerExists = true;}}
        } catch (SQLException e) {e.printStackTrace();}
        return isPlayerExists;
    }

    public void addPlayer(String player) {
        try (PreparedStatement statement = co.prepareStatement(
                "INSERT INTO players (player, COCQ, performedID) VALUES (?, ?, ?)")) {
            statement.setObject(1, player);
            statement.setObject(2, 0);
            statement.setObject(3, -1);
            statement.execute();
        } catch (SQLException e) {e.printStackTrace();}
    }

    public int getPerformedQuestID(String player) {
        try (PreparedStatement statement = co.prepareStatement("SELECT `performedID` FROM `players` WHERE `player` = ?")) {
            statement.setObject(1, player);

            ResultSet rs = statement.executeQuery();
            if(rs.next()){return rs.getInt(1);}
        } catch (SQLException e) {e.printStackTrace();}
        return -1;
    }

    public int getCOCQ(String player) {
        try (PreparedStatement statement = co.prepareStatement("SELECT `COCQ` FROM `players` WHERE `player` = ?")) {
            statement.setObject(1, player);

            ResultSet rs = statement.executeQuery();
            if(rs.next()){return rs.getInt(1);}
        } catch (SQLException e) {e.printStackTrace();}
        return -1;
    }


    public void setPerformedQuestID(String player, Integer integer) {
        try (PreparedStatement statement = co.prepareStatement("UPDATE `players` SET `performedID` = ? WHERE `player` = ?")) {
            statement.setObject(1, integer);
            statement.setObject(2, player);

            statement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void setCOCQ(String player, Integer integer) {
        try (PreparedStatement statement = co.prepareStatement("UPDATE `players` SET `COCQ` = ? WHERE `player` = ?")) {
            statement.setObject(1, integer);
            statement.setObject(2, player);

            statement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
    }
}