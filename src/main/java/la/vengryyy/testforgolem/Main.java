package la.vengryyy.testforgolem;

import la.vengryyy.testforgolem.commands.questsCMD;
import la.vengryyy.testforgolem.commands.skiptime;
import la.vengryyy.testforgolem.events.onBlockBreak;
import la.vengryyy.testforgolem.events.onEntityDeath;
import la.vengryyy.testforgolem.events.onInventoryClick;
import la.vengryyy.testforgolem.events.onPlayerJoin;
import la.vengryyy.testforgolem.managers.DataBase;
import la.vengryyy.testforgolem.managers.GUI;
import la.vengryyy.testforgolem.managers.QUESTS;
import la.vengryyy.testforgolem.managers.TEXT;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;

public final class Main extends JavaPlugin {

    public GUI guiManager;
    public TEXT textManager; // Да, мне так удобней чем конфигом.
    public QUESTS questsManager;
    public DataBase db;

    @Override
    public void onEnable() {
        System.out.println("TestForGolem enabled!");

        File f = new File(this.getDataFolder() + "/");
        if(!f.exists()) {
            f.mkdir();
        }

        guiManager = new GUI(this);
        textManager = new TEXT();
        questsManager = new QUESTS();
        db = new DataBase(this);
        db.connectToDB();

        questsManager.loadQuests();
        questsManager.CountOfQuests = questsManager.Quests.size();
        questsManager.AvailableQuestID = new Random().nextInt(3);

        Objects.requireNonNull(getCommand("quests")).setExecutor(new questsCMD(this));
        Objects.requireNonNull(getCommand("skiptime")).setExecutor(new skiptime(this));
        getServer().getPluginManager().registerEvents(new onInventoryClick(this), this);
        getServer().getPluginManager().registerEvents(new onPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new onBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new onEntityDeath(this), this);

        questsManager.timeOfLastQuestUpdate = LocalTime.now();
        scheduler();
    }

    @Override
    public void onDisable() {
        System.out.println("TestForGolem disabled!");
        try {
            db.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void scheduler() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> questsManager.updateTime(), 0L, 20L);
    }
}
