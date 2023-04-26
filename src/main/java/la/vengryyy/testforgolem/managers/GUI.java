package la.vengryyy.testforgolem.managers;

import la.vengryyy.testforgolem.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GUI implements Listener {
    private final Main plugin;

    public GUI(Main plugin) {
        this.plugin = plugin;
    }

    public Inventory createQuestsGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27, this.plugin.textManager.questsGuiTitle + " " + player.getName());

        inventory.setItem(10, getQuestInfo(player));
        inventory.setItem(16, getInfoAboutPlayer(player));

        return inventory;
    }

    private ItemStack getInfoAboutPlayer(Player player) {
        int countOfCompletedQuests = this.plugin.db.getCOCQ(player.getName());
        String COCQstring = this.plugin.textManager.COCQ + countOfCompletedQuests;
        LocalTime currentTime = LocalTime.now();
        Duration timeSinceLastUpdate = Duration.between(this.plugin.questsManager.timeOfLastQuestUpdate, currentTime);
        int timeToUpdateQuests = Math.round(60 - timeSinceLastUpdate.toMinutes());
        String TTUQstring = this.plugin.textManager.timeToUpdateQuests + timeToUpdateQuests + "м";
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(this.plugin.textManager.questsInfoAboutPlayerTitle);
        List<String> lore = new ArrayList<>();
        lore.add(COCQstring);
        lore.add(TTUQstring);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    private ItemStack getQuestInfo(Player player) {
        ItemStack item;
        ItemMeta itemMeta;
        List<String> lore = new ArrayList<>();
        if (this.plugin.db.getPerformedQuestID(player.getName()) == -1 && !this.plugin.questsManager.lastQuestTaken.contains(player)) {
            item = new ItemStack(Material.GOLD_BLOCK);
            itemMeta = item.getItemMeta();
            assert itemMeta != null;
            int AvailableQuestID = this.plugin.questsManager.AvailableQuestID + 1;
            itemMeta.setDisplayName(this.plugin.textManager.newQuestTitle + AvailableQuestID);
            switch (AvailableQuestID) {
                case 1:
                    lore.add(this.plugin.textManager.questInfo + this.plugin.questsManager.Quest1Info);
                    break;
                case 2:
                    lore.add(this.plugin.textManager.questInfo + this.plugin.questsManager.Quest2Info);
                    break;
                case 3:
                    lore.add(this.plugin.textManager.questInfo + this.plugin.questsManager.Quest3Info);
                    break;
            }
            lore.add("");
            lore.add(this.plugin.textManager.takeQuest);
        } else if (this.plugin.questsManager.lastQuestTaken.contains(player)) {
            System.out.println(1);
            item = new ItemStack(Material.DIAMOND_BLOCK);
            itemMeta = item.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName(this.plugin.textManager.questAlreadyCompleted);
            lore.add(this.plugin.textManager.questAlreadyCompletedLore);
        } else {
            item = new ItemStack(Material.EMERALD_BLOCK);
            itemMeta = item.getItemMeta();
            assert itemMeta != null;
            int PerformedQuestID = this.plugin.db.getPerformedQuestID(player.getName()) + 1;
            itemMeta.setDisplayName(this.plugin.textManager.availableQuestTitle + PerformedQuestID);
            switch (PerformedQuestID) {
                case 1:
                    lore.add(this.plugin.textManager.questInfo + this.plugin.questsManager.Quest1Info);
                    break;
                case 2:
                    lore.add(this.plugin.textManager.questInfo + this.plugin.questsManager.Quest2Info);
                    break;
                case 3:
                    lore.add(this.plugin.textManager.questInfo + this.plugin.questsManager.Quest3Info);
                    break;
            }
            lore.add("");
            lore.add(this.plugin.textManager.cancelQuest);
        }


        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(9002);
        item.setItemMeta(itemMeta);
        return item;
    }
}
